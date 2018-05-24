package com.solar.mybatis.generator.comment;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by hushaoge on 2016/11/9.
 */
public class MyDefaultCommentGenerator extends DefaultCommentGenerator {
    private Properties properties;
    private Properties systemPro;
    private boolean suppressDate;
    private boolean suppressAllComments;
    private String currentDateStr;

    public MyDefaultCommentGenerator() {
        super();
        properties = new Properties();
        systemPro = System.getProperties();
        suppressDate = false;
        suppressAllComments = false;
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
    }

    public void addComment(XmlElement xmlElement) {
        if(!this.suppressAllComments) {
            xmlElement.addElement(new TextElement("<!--"));
            StringBuilder sb = new StringBuilder();
            sb.append("  WARNING - ");
            sb.append("@mbggenerated");
            sb.append(" Automatically generated");
            String s = this.getDateString();
            if(s != null) {
                sb.append(" on ");
                sb.append(s);
                sb.append('.');
            }
            xmlElement.addElement(new TextElement(sb.toString()));
            xmlElement.addElement(new TextElement("-->"));
        }
    }

    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        this.suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate"));
        this.suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
    }

    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
        //javaElement.addJavaDocLine(" *");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append("@mbggenerated");
        if(markAsDoNotDelete) {
            sb.append(" do_not_delete_during_merge");
        }

        String s = this.getDateString();
        if(s != null) {
            sb.append(' ');
            sb.append(s);
        }

        javaElement.addJavaDocLine(sb.toString());
    }

    protected String getDateString() {
        return this.suppressDate?null:this.currentDateStr;
    }



    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        if(!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            innerEnum.addJavaDocLine("/**");
            sb.append(" * database table ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            innerEnum.addJavaDocLine(sb.toString());
            this.addJavadocTag(innerEnum, false);
            innerEnum.addJavaDocLine(" */");
        }
    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if(!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            field.addJavaDocLine("/**");
            sb.append(" * ");
            sb.append(introspectedColumn.getActualColumnName());
            field.addJavaDocLine(sb.toString());
            this.addJavadocTag(field, false);
            field.addJavaDocLine(" */");
        }
    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if(!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            field.addJavaDocLine("/**");
            field.addJavaDocLine(sb.toString());
            this.addJavadocTag(field, false);
            field.addJavaDocLine(" */");
        }
    }

    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        if(!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            method.addJavaDocLine("/**");
            sb.append(" * database table ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            method.addJavaDocLine(sb.toString());
            this.addJavadocTag(method, false);
            method.addJavaDocLine(" */");
        }
    }

    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if(!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            method.addJavaDocLine("/**");
            sb.setLength(0);
            sb.append(" * @return the value of ");
            sb.append(introspectedColumn.getActualColumnName());
            method.addJavaDocLine(sb.toString());
            this.addJavadocTag(method, false);
            method.addJavaDocLine(" */");
        }
    }

    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if(!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            method.addJavaDocLine("/**");
            Parameter parm = (Parameter)method.getParameters().get(0);
            sb.setLength(0);
            sb.append(" * @param ");
            sb.append(parm.getName());
            sb.append(" the value for ");
            sb.append(introspectedColumn.getActualColumnName());
            method.addJavaDocLine(sb.toString());
            this.addJavadocTag(method, false);
            method.addJavaDocLine(" */");
        }
    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if(!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            innerClass.addJavaDocLine("/**");
            sb.append(" * database table ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            innerClass.addJavaDocLine(sb.toString());
            this.addJavadocTag(innerClass, false);
            innerClass.addJavaDocLine(" */");
        }
    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if(!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            innerClass.addJavaDocLine("/**");
            sb.append(" *  database table ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            innerClass.addJavaDocLine(sb.toString());
            this.addJavadocTag(innerClass, markAsDoNotDelete);
            innerClass.addJavaDocLine(" */");
        }
    }
}
