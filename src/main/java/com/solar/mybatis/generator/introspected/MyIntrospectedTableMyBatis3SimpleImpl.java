package com.solar.mybatis.generator.introspected;

import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3SimpleImpl;

/**
 * Created by hushaoge on 2016/11/9.
 */
public class MyIntrospectedTableMyBatis3SimpleImpl extends IntrospectedTableMyBatis3SimpleImpl {
    @Override
    protected String calculateMyBatis3FallbackSqlMapNamespace() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.calculateSqlMapPackage());
        sb.append('.');
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("DAO");
        return sb.toString();
    }

    @Override
    protected void calculateJavaClientAttributes() {
        if(this.context.getJavaClientGeneratorConfiguration() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.calculateJavaClientImplementationPackage());
            sb.append('.');
            sb.append(this.fullyQualifiedTable.getDomainObjectName());
            sb.append("DAOImpl");
            this.setDAOImplementationType(sb.toString());
            sb.setLength(0);
            sb.append(this.calculateJavaClientInterfacePackage());
            sb.append('.');
            sb.append(this.fullyQualifiedTable.getDomainObjectName());
            sb.append("DAO");
            this.setDAOInterfaceType(sb.toString());
            sb.setLength(0);
            sb.append(this.calculateJavaClientInterfacePackage());
            sb.append('.');
            sb.append(this.fullyQualifiedTable.getDomainObjectName());
            // 文件名改成以DAO结尾
            sb.append("DAO");
            this.setMyBatis3JavaMapperType(sb.toString());
            sb.setLength(0);
            sb.append(this.calculateJavaClientInterfacePackage());
            sb.append('.');
            sb.append(this.fullyQualifiedTable.getDomainObjectName());
            sb.append("SqlProvider");
            this.setMyBatis3SqlProviderType(sb.toString());
        }
    }

    @Override
    protected String calculateMyBatis3XmlMapperFileName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        //文件名改成以DAO结尾
        sb.append("DAO.xml");
        return sb.toString();
    }
}
