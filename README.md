# Development Notes

On how to create JAX-WS web service using Tomcat 7.0, reference JAX-WS implementation,
Maven, Eclipse WTP (Juno) and deploy it to Uhuru AppCloud.

## Create Web Service

In your Eclipse workspace create new Maven project. Skip archetype selection, it's 
unnecessary. Provide group ID, artifact ID and version. Save.

Then, open project preferences (Alt + Enter), expand Project Facets, click
_Change to Facated project" and from the list of available facets select 
_Dynamic Web Project_. From the list of runtimes on the right shoose your Tomcat server 
(create new server if it is not available). If Tomcat is not available on this list, 
and it's grayed out when you select _show all runtimes_, it means you have to change 
Java version facet (because Tomcat server is using different JRE then you choose for 
your project).

This will change Maven project layout to the one you know from Dynamic Web Project.

Now go to _Deployment Assembly_ and remove _WebContent_ from the list. Package project
with Maven:

```
$ mvn clean package
```

Change packaging structure in project preferences (this is required for Tomcat hot 
deployment and dynamic reloading):
* source `/src/main/webapp`, desctination `/` 
* source `/target/uhuru-test-1/WEB-INF/lib`, destination `WEB-INF/lib`

After deploying webservice to Tomcat you can now find your service under 
this URL (assume Tomcat is using port 81, but default is 8080):

```
http://localhost:81/uhuru-test-1/services/Echo
```

And WSDL is available here:

```
http://localhost:81/uhuru-test-1/services/Echo?wsdl
```

You can now use Run / Start Web Service Explorer to check if your web service works 
correctly.

Build WAR:

```
$ mvn clean package
```

## Deploy to Uhuru Cloud

Now you can deploy WAR to Uhuru Cloud (instructions available [here](support.uhurusoftware.com/entries/21454287-deploying-and-managing-apps-with-command-line)).

I'm using `vmcu` command line tool and therefore I will follow this path in this 
notes, but you should be aware that you can deploy your application in a different way.

```
$ vmcu target services.uhurucloud.com
$ vmcu login
```

Provide your token when asked. Token can be obtained from your Uhuru dashboard 
(click on the blue rounded rectangular bullets on the right side to get one-time 
login token).

```
$ vmcu cloud-team <cloud-name>
$ cd target
$ vmcu push 
$ vmcu start <app-name>
```

Then just use your application happily ever after.

You can also use CloudManager GUI to do the same thing, but it works correctly only
on Windows systems, I guess.

Just FYI, service is using around 85MB of RAM (I set max heap size to 128M).

## License

Copyright (C) 2012 Bartosz Firyn

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.




