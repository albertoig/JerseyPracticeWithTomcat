Estos ejemplos con Jersey se basan sobre todo en un servidor tomcat, por lo tanto, todas las configuraciones especificadas aquí serán con Tomcat 8 con JDK 8, también necesitaremos usar como IDE el IntelliJ versión 14.

Para seguir los ejemplos de Jersey necesitamos seguir las siguientes pre-configuraciones:

TOMCAT 8
--------
Para desplegar todo sobre nuestra maquina local necesitaremos crear un nuevo host, para ello modificaremos nuestro server.xml de la siguiente manera:

```
    <Host name="dev.jersey.com"  appbase="jersey" unpackWARs="true" autoDeploy="true">
            <Alias>dev.jersey.com</Alias>

            <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
            prefix="devjerseycom_access_log" suffix=".txt"
            pattern="%h %l %u %t %r %s %b" />

            <Context path="" docBase="/usr/local/apache-tomcat-8.0.26/webapps/jersey" debug="0" reloadable="true" />
    </Host>
```

Con esto crearemos un nuevo host con la URL en dev.jersey.com que cogera la carpeta base jersey dentro de webapps de tomcat. Como dato adicional creará los logs con su propio prefijo. 
Además deberemos añadir a nuestro /etc/hosts la configuración para la redirección DNS:

```
127.0.0.1       dev.jersey.com
```

Referencias: 
[http://www.ramkitech.com/2012/02/understanding-virtual-host-concept-in.html link](http://www.ramkitech.com/2012/02/understanding-virtual-host-concept-in.html)

IntelliJ
--------
Recomiendo de momento este articulo: 
[https://medium.com/@jamsesso/starting-out-with-jersey-apache-tomcat-using-intellij-6338d93ffd40 link](https://medium.com/@jamsesso/starting-out-with-jersey-apache-tomcat-using-intellij-6338d93ffd40)


Configuración del servlet
-------------------------
Para que todas las llamadas vayan al path /services/ tenemos que insertar la siguiente configuración en el web.xml:
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
    <servlet>
        <servlet-name>Example API</servlet-name>
        <servlet-class>com.sun.jersey.spi.container.servlet.ServletContainer</servlet-class>

        <init-param>
            <param-name>com.sun.jersey.config.property.packages</param-name>
            <param-value>com.albertoig.jersey</param-value>
        </init-param>

        <init-param>
            <param-name>com.sun.jersey.api.json.POJOMappingFeature</param-name>
            <param-value>true</param-value>
        </init-param>
    </servlet>

    <servlet-mapping>
        <servlet-name>Example API</servlet-name>
        <url-pattern>/service/*</url-pattern>
    </servlet-mapping>
</web-app>
```




