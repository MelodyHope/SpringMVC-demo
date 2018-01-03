package com.tch.config;

import java.io.File;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class MyPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	private final static Logger log = LoggerFactory
			.getLogger(MyPropertyPlaceholderConfigurer.class);

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#processProperties(org.springframework.beans.factory.config.ConfigurableListableBeanFactory, java.util.Properties)
	 */
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		// TODO 自动生成的方法存根
		log.info("Enter MyPropertyPlaceholderConfigurer.processProperties");
		if(props!=null)props.put("package.name3", "com.tch6");
		super.processProperties(beanFactoryToProcess, props);
	}

	/* (non-Javadoc)
	 * @see org.springframework.core.io.support.PropertiesLoaderSupport#setLocations(org.springframework.core.io.Resource[])
	 */
	@Override
	public void setLocations(Resource[] locations) {
		// TODO 自动生成的方法存根
		String userHome = System.getProperty("user.home");
		String filePath = userHome + File.separator + "notice.properties";
		Resource tempResource = new FileSystemResource(filePath);
		if(locations!=null){
			Resource[] temp = new Resource[locations.length+1];
			System.arraycopy(locations, 0, temp, 0, locations.length);
			temp[locations.length] = tempResource;
			super.setLocations(temp);
		}else{
			super.setLocations(locations);
		}
	}

}

