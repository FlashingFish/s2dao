package org.seasar.dao.impl;

import javax.sql.DataSource;

import org.seasar.dao.BeanMetaData;
import org.seasar.extension.jdbc.PropertyType;
import org.seasar.extension.jdbc.StatementFactory;

/**
 * @author higa
 *  
 */
public class DeleteAutoHandler extends AbstractAutoHandler {

	public DeleteAutoHandler(DataSource dataSource,
			StatementFactory statementFactory,
			BeanMetaData beanMetaData, PropertyType[] propertyTypes) {

		super(dataSource, statementFactory, beanMetaData, propertyTypes);
	}

	protected void setupBindVariables(Object bean) {
		setupDeleteBindVariables(bean);
	}
}