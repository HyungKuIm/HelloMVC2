package ServletStudy;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ClassPathXmlApplicationContext implements BeanFactory{

	private Map<String, Object> beanMap = new HashMap<>();
	private String path = "applicationContext.xml";
	
	public ClassPathXmlApplicationContext() {
		this("applicationContext.xml");
	}
	
	public ClassPathXmlApplicationContext(String path) {
		if (StringUtils.isEmpty(path)) {
			throw new RuntimeException("IOC경로가 없습니다.");
		}
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(inputStream);
			
			NodeList beanNodeList = document.getElementsByTagName("bean");
			for (int i = 0; i < beanNodeList.getLength(); i++) {
				Node beanNode = beanNodeList.item(i);
				if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
					Element beanElement = (Element) beanNode;
					String beanId = beanElement.getAttribute("id");
					String className = beanElement.getAttribute("class");
					Class beanClass = Class.forName(className);
					Object beanObj = beanClass.newInstance();
					beanMap.put(beanId, beanObj);
					
				}
			}
			
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Object getBean(String id) {
		return beanMap.get(id);
	}

}
