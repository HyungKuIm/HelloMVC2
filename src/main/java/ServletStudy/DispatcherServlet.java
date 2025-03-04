package ServletStudy;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

//import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

	private BeanFactory beanFactory;
	
	public DispatcherServlet() {
		
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext application = getServletContext();
		Object beanFactoryObj = application.getAttribute("beanFactory");
		if (beanFactoryObj != null) {
			beanFactory = (BeanFactory) beanFactoryObj;
		} else {
			throw new RuntimeException("IOC 팩토리 구성 실패");
		}
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(1);
		int lastDotIndex = servletPath.lastIndexOf(".do");
		servletPath = servletPath.substring(0,  lastDotIndex);
		Object controllerBeanObj = beanFactory.getBean(servletPath);
		String operate = request.getParameter("operate");
		if (StringUtils.isEmpty(operate)) {
			operate = "index";
		}
		try {
			Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
			for (Method method : methods) {
				if (operate.equals(method.getName())) {
					Parameter[] parameters = method.getParameters();
					Object[] parameterValues = new Object[parameters.length];
					for (int i=0; i<parameters.length; i++) {
						Parameter parameter = parameters[i];
						String parameterName = parameter.getName();
						if ("request".equals(parameterName)) {
							parameterValues[i] = request;
						} else if ("response".equals(parameterName)) {
							parameterValues[i] = response;
						} else if ("session".equals(parameterName)) {
							parameterValues[i] = request.getSession();
						} else {
							String parameterValue = request.getParameter(parameterName);
							String typeName = parameter.getType().getName();
							Object parameterObj = parameterValue;
							if (parameterObj != null) {
								if ("java.lang.Integer".equals(typeName)) {
									parameterObj = Integer.parseInt(parameterValue);
								}
							}
							parameterValues[i] = parameterObj;
						}
					}
					method.setAccessible(true);
					Object returnObj = method.invoke(controllerBeanObj, parameterValues);
					
					String methodReturnStr = (String) returnObj;
					if (methodReturnStr.startsWith("reddirect:")) {
						String redirectStr = methodReturnStr.substring("redirect:".length());
						response.sendRedirect(redirectStr);
					} else {
						super.processTemplate(methodReturnStr, request, response);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DispatcherServletException("DispatcherServlet 오류발생!");
		}
	}
}





















