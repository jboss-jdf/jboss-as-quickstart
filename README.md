Red Hat JBoss Enterprise Application Platform (EAP) Quickstarts
====================
Summary: The quickstarts demonstrate Java EE 6 and a few additional technologies from the JBoss stack. They provide small, specific, working examples that can be used as a reference for your own project.

Introduction
------------


These quickstarts run on Red Hat JBoss Enterprise Application Platform 6.1 or later. We recommend using the JBoss EAP ZIP file. This version uses the correct dependencies and ensures you test and compile against your runtime environment. 

Be sure to read this entire document before you attempt to work with the quickstarts. It contains the following information:

* [Available Quickstarts](#available-quickstarts): List of the available quickstarts and details about each one.

* [Suggested Approach to the Quickstarts](#suggested-approach-to-the-quickstarts): A suggested approach on how to work with the quickstarts.

* [System Requirements](#system-requirements): List of software required to run the quickstarts.

* [Configure Maven](#configure-maven): How to configure the Maven repository for use by the quickstarts.

* [Run the Quickstarts](#run-the-quickstarts): General instructions for building, deploying, and running the quickstarts.

* [Run the Arquillian Tests](#run-the-arquillian-tests): How to run the Arquillian tests provided by some of the quickstarts.

* [Optional Components](#optional-components): How to install and configure optional components required by some of the quickstarts.


Available Quickstarts
---------------------

The list of all currently available quickstarts can be found here: <http://site-jdf.rhcloud.com/quickstarts/get-started/>. The table lists each quickstart name, the technologies it demonstrates, gives a brief description of the quickstart, and the level of experience required to set it up. For more detailed information about a quickstart, click on the quickstart name.

Some quickstarts are designed to enhance or extend other quickstarts. These are noted in the **Prerequisites** column. If a quickstart lists prerequisites, those must be installed or deployed before working with the quickstart.

Quickstarts with tutorials in the [Get Started Developing Applications](http://www.jboss.org/jdf/quickstarts/jboss-as-quickstart/guide/Introduction/ "Get Started Developing Applications") are noted with two asterisks ( ** ) following the quickstart name. 

_Note: Some of these quickstart use the H2 database included with JBoss EAP 6. It is a lightweight, relational example datasource that is used for examples only. It is not robust or scalable and should NOT be used in a production environment!_

[TOC-quickstart]

Suggested Approach to the Quickstarts
-------------------------------------

We suggest you approach the quickstarts as follows:

* Regardless of your level of expertise, we suggest you start with the **helloworld** quickstart. It is the simplest example and is an easy way to prove your server is configured and started correctly.
* If you are a beginner or new to JBoss, start with the quickstarts labeled **Beginner**, then try those marked as **Intermediate**. When you are comfortable with those, move on to the **Advanced** quickstarts.
* Some quickstarts are based upon other quickstarts but have expanded capabilities and functionality. If a prerequisite quickstart is listed, be sure to deploy and test it before looking at the expanded version.


System Requirements
-------------------

The applications these projects produce are designed to be run on Red Hat JBoss Enterprise Application Platform 6.1 or later. 

To run these quickstarts with the provided build scripts, you need the following:

1. Java 1.6, to run JBoss AS and Maven. You can choose from the following:
    * OpenJDK
    * Oracle Java SE
    * Oracle JRockit

2. Maven 3.0.0 or newer, to build and deploy the examples
    * If you have not yet installed Maven, see the [Maven Getting Started Guide](http://maven.apache.org/guides/getting-started/index.html) for details.
    * If you have installed Maven, you can check the version by typing the following in a command prompt:

            mvn --version 

3. The JBoss EAP distribution ZIP.
    * For information on how to install and run JBoss, refer to the product documentation.

4. You can also use [JBoss Developer Studio or Eclipse](#use-jboss-developer-studio-or-eclipse-to-run-the-quickstarts) to run the quickstarts. 


Configure Maven
---------------

### Configure Maven to Build and Deploy the Quickstarts

The quickstarts use artifacts located in the JBoss GA and Early Access repositories. You must configure Maven to use these repositories before you build and deploy the quickstarts. 

_Note: These instructions assume you are working with a released version of the quickstarts. If you are working with the quickstarts located in the GitHub master branch, follow the instructions located in the [Contributing Guide](CONTRIBUTING.md#configure-maven)._

1. Locate the Maven install directory for your operating system. It is usually installed in `${user.home}/.m2/`. 

            For Linux or Mac:   ~/.m2/
            For Windows: "\Documents and Settings\USER_NAME\.m2\"  -or-  "\Users\USER_NAME\.m2\"

2. If you have an existing `settings.xml` file, rename it so you can restore it later.

            For Linux or Mac:  mv ~/.m2/settings.xml ~/.m2/settings-backup.xml
            For Windows: ren "\Documents and Settings\USER_NAME\.m2\settings.xml" settings-backup.xml
                    -or- ren "\Users\USER_NAME\.m2\settings.xml" settings-backup.xml
                   
3. If you have an existing `repository/` directory, rename it so you can restore it later. For example

            For Linux or Mac:  mv ~/.m2/repository/ ~/.m2/repository-backup/
            For Windows: ren "\Documents and Settings\USER_NAME\.m2\repository\" repository-backup
                    -or- ren "\Users\USER_NAME\.m2\repository\" repository-backup
4. Copy the `settings.xml` file from the root of the quickstarts directory to your Maven install directory.
 
            For Linux or Mac:  cp QUICKSTART_HOME/settings.xml  ~/.m2/settings.xml
            For Windows: copy QUICKSTART_HOME/settings.xml "\Documents and Settings\USER_NAME\.m2\settings.xml"
                    -or- copy QUICKSTART_HOME/settings.xml "\Users\USER_NAME\.m2\settings.xml"

_Note:_ If you do not wish to configure the Maven settings, you must pass the configuration setting on every Maven command as follows: ` -s QUICKSTART_HOME/settings.xml`

### Restore Your Maven Configuration When You Finish Testing the Quickstarts

1. Locate the Maven install directory for your operating system. It is usually installed in `${user.home}/.m2/`. 

            For Linux or Mac:   ~/.m2/
            For Windows: "\Documents and Settings\USER_NAME\.m2\"  -or-  "\Users\USER_NAME\.m2\"

2. Restore the `settings.xml` file/

            For Linux or Mac:  mv ~/.m2/settings-backup.xml ~/.m2/settings.xml
            For Windows: ren "\Documents and Settings\USER_NAME\.m2\settings-backup.xml" settings.xml
                    -or- ren "\Users\USER_NAME\.m2\settings-backup.xml" settings.xml
                   
3. Restore the `repository/` directory

            For Linux or Mac:  mv ~/.m2/repository-backup/ ~/.m2/repository/
            For Windows: ren "\Documents and Settings\USER_NAME\.m2\repository-backup\" repository
                    -or- ren "\Users\USER_NAME\.m2\repository-backup\" repository
            

### Maven Profiles

Profiles are used by Maven to customize the build environment. The `pom.xml` in the root of the quickstart directory defines the following profiles:

* The `default` profile defines the list of modules or quickstarts that require nothing but JBoss Enterprise Application Platform.
* The `requires-postgres` profile lists the quickstarts that require PostgreSQL to be running when the quickstart is deployed.
* The `complex-dependency` profile lists quickstarts that require manual configuration that can not be automated.
* The `requires-full` profile lists quickstarts the require you start the JBoss server using the full profile.
* The `requires-xts` profile lists quickstarts the require you start the JBoss server using the xts profile.
* The `non-maven` profile lists quickstarts that do not require Maven, for example, quickstarts that depend on deployment of other quickstarts or those that use other Frameworks such as Forge.


Run the Quickstarts
-------------------

The root folder of each individual quickstart contains a README file with specific details on how to build and run the example. In most cases you do the following:

* [Start the JBoss server](#start-the-jboss-server)
* [Build and deploy the quickstarts](#build-and-deploy-the-quickstarts)


### Start the JBoss Server

Before you deploy a quickstart, in most cases you need a running JBoss EAP server. A few of the Arquillian tests do not require a running server. This will be noted in the README for that quickstart. 

The JBoss server can be started a few different ways.

* [Start the Default JBoss Server](#start-the-default-jboss-server): This is the default configuration. It defines minimal subsystems and services.
* [Start the JBoss Server with the _full_ profile](#start-the-jboss-server-with-the-full-profile): This profile configures many of the commonly used subsystems and services.
* [Start the JBoss Server with a custom configuration](#start-the-jboss-server-with-custom-configuration-options): Custom configuration parameters can be specified on the command line when starting the server.

The README for each quickstart will specify which configuration is required to run the example.

#### Start the Default JBoss Server

To start JBoss EAP:

1. Open a command prompt and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the JBoss server:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

#### Start the JBoss Server with the Full Profile

To start JBoss EAP with the Full Profile:

1. Open a command prompt and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the JBoss server with the full profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh -c standalone-full.xml
        For Windows: JBOSS_HOME\bin\standalone.bat -c standalone-full.xml

#### Start the JBoss Server with Custom Configuration Options

To start JBoss EAP with custom configuration options:

1. Open a command prompt and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the JBoss server. Replace the CUSTOM_OPTIONS with the custom optional parameters specified in the quickstart.

        For Linux:   JBOSS_HOME/bin/standalone.sh CUSTOM_OPTIONS
        For Windows: JBOSS_HOME\bin\standalone.bat CUSTOM_OPTIONS
           
### Build and Deploy the Quickstarts

See the README file in each individual quickstart folder for specific details and information on how to run and access the example.

#### Build the Quickstart Archive

In some cases, you may want to build the application to test for compile errors or view the contents of the archive. 

1. Open a command prompt and navigate to the root directory of the quickstart you want to build.
2. Use this command if you only want to build the archive, but not deploy it:

            mvn clean install

#### Build and Deploy the Quickstart Archive

1. Make sure you [start the JBoss server](#start-the-jboss-server) as described in the README.
2. Open a command prompt and navigate to the root directory of the quickstart you want to run.
3. Use this command to build and deploy the archive:

            mvn clean install jboss-as:deploy

#### Undeploy an Archive

The command to undeploy the quickstart is simply: 

        mvn jboss-as:undeploy
 
### Verify the Quickstarts Build with One Command
-------------------------------------------------

You can verify the quickstarts build using one command. However, quickstarts that have complex dependencies must be skipped. For example, the _jax-rs-client_ quickstart is a RESTEasy client that depends on the deployment of the _helloworld-rs_ quickstart. As noted above, the root `pom.xml` file defines a `complex-dependencies` profile to exclude these quickstarts from the root build process. 

To build the quickstarts:

1. Do not start the JBoss server.
2. Open a command prompt and navigate to the root directory of the quickstarts.
3. Use this command to build the quickstarts that do not have complex dependencies:

            mvn clean install '-Pdefault,!complex-dependencies'

_Note_: If you see a `java.lang.OutOfMemoryError: PermGen space` error when you run this command, increase the memory by typing the following command for your operating system, then try the above command again.

        For Linux:   export MAVEN_OPTS="-Xmx512m -XX:MaxPermSize=128m"
        For Windows: SET MAVEN_OPTS="-Xmx512m -XX:MaxPermSize=128m"


### Undeploy the Deployed Quickstarts with One Command
------------------------------------------------------

To undeploy the quickstarts from the root of the quickstart folder, you must pass the argument `-fae` (fail at end) on the command line. This allows the command to continue past quickstarts that fail due to complex dependencies and quickstarts that only have Arquillian tests and do not deploy archives to the server.

You can undeploy quickstarts using the following procedure:

1. Start the JBoss server.
2. Open a command prompt and navigate to the root directory of the quickstarts.
3. Use this command to undeploy any deployed quickstarts:

            mvn jboss-as:undeploy -fae

To undeploy any quickstarts that fail due to complex dependencies, follow the undeploy procedure described in the quickstart's README file.


Run the Arquillian Tests
------------------------

Some of the quickstarts provide Arquillian tests. By default, these tests are configured to be skipped, as Arquillian tests an application on a real server, not just in a mocked environment.

You can either start the server yourself or let Arquillian manage its lifecycle during the testing. The individual quickstart README should tell you what to expect in the console output and the server log when you run the test.


1. Test the quickstart on a remote server
    * Arquillian's remote container adapter expects a JBoss server instance to be already started prior to the test execution. You must [Start the JBoss server](#start-the-jboss-server) as described in the quickstart README file.
    * If you need to run the tests on a JBoss server running on a machine other than localhost, you can configure this, along with other options, in the `src/test/resources/arquillian.xml` file using the following properties:
        
            <container qualifier="jboss" default="true">
                <configuration>
                    <property name="managementAddress">myhost.example.com</property>
                    <property name="managementPort">9999</property>
                    <property name="username">customAdminUser</property>
                    <property name="password">myPassword</property>
                </configuration>
            </container>    
    * Run the test goal with the following profile activated:

            mvn clean test -Parq-jbossas-remote     
2. Test the quickstart on a managed server

    Arquillian's managed container adapter requires that your server is not running as it will start the container for you. However, you must first let it know where to find the JBoss server directory. The simplest way to do this is to set the `JBOSS_HOME` environment variable to the full path to your JBoss server directory. Alternatively, you can set the path in the `jbossHome` property in the Arquillian configuration file.
    * Open the `src/test/resources/arquillian.xml` file located in the quickstart directory.
    * Find the configuration for the JBoss container. It should look like this:

            <!-- Example configuration for a managed/remote JBoss EAP 6 instance -->
            <container qualifier="jboss" default="true">
                <!-- If you want to use the JBOSS_HOME environment variable, just delete the jbossHome property -->
                <!--<configuration> -->
                <!--<property name="jbossHome">/path/to/jboss/as</property> -->
                <!--</configuration> -->
            </container>           
    * Uncomment the `configuration` element, find the `jbossHome` property and replace the "/path/to/jboss/as" value with the actual path to your JBoss EAP server.
    * Run the test goal with the following profile activated:

            mvn clean test -Parq-jbossas-managed


Use JBoss Developer Studio or Eclipse to Run the Quickstarts
------------------------------------------------------------

You can also deploy the quickstarts from Eclipse using JBoss tools. For more information on how to set up Maven and the JBoss tools, refer to the [JBoss Enterprise Application Platform Development Guide](https://access.redhat.com/site/documentation/JBoss_Enterprise_Application_Platform/) or [Get Started Developing Applications](http://www.jboss.org/jdf/quickstarts/jboss-as-quickstart/guide/Introduction/ "Get Started Developing Applications").


Optional Components
-------------------
The following components are needed for only a small subset of the quickstarts. Do not install or configure them unless the quickstart requires it.

* [Create Users Required by the Quickstarts](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CREATE_USERS.md#create-users-required-by-the-quickstarts): Add a Management or Application user for the quickstarts that run in a secured mode.

* [Configure the PostgreSQL Database for Use with the Quickstarts](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_POSTGRESQL.md#configure-the-postgresql-database-for-use-with-the-quickstarts): The PostgreSQL database is used for the distributed transaction quickstarts.

* [Configure Byteman for Use with the Quickstarts](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_BYTEMAN.md#configure-byteman-for-use-with-the-quickstarts): This tool is used to demonstrate crash recovery for distributed transaction quickstarts.


### Add a Management or Application User

By default, JBoss EAP is now distributed with security enabled for the management interfaces. 
A few of the quickstarts use these management interfaces and require that you create a management or application user to access the running application. 
An `add-user` script is provided in the `JBOSS_HOME/bin` directory for that purpose. 
You can run the script interactively or you can pass arguments on the command line.

The following procedures describe how to add a user with the appropriate permissions to run the quickstarts that depend on them.

#### Add a Management User

You can pass arguments on the command line or you can run the script interactively.

##### Add a Management User Passing Arguments on the Command Line

You can create the management user non-interactively by passing each argument on the command line. 

For example, to add the Management User `admin` in the default `ManagementRealm` realm with password `adminPass1!`, 
open a command prompt and type the following:

        For Linux:   JBOSS_HOME/bin/add-user.sh -u 'admin' -p 'adminPass1!'
        For Windows: JBOSS_HOME\bin\add-user.bat -u 'admin' -p 'adminPass1!'

##### Add a Management User Interactively

If you prefer, you can create the management user interactively. 

1. Open a command prompt.
2. Type the command for your operating system

        For Linux:   JBOSS_HOME/bin/add-user.sh
        For Windows: JBOSS_HOME\bin\add-user.bat
3. You should see the following response:

        What type of user do you wish to add? 

        a) Management User (mgmt-users.properties) 
        b) Application User (application-users.properties)
        (a):

    At the prompt, press enter to use the default Management User.
4. You should see the following response:

        Enter the details of the new user to add.
        Using realm 'ManagementRealm' as discovered from the existing property files.
        Username :
5. Enter the Username and, at the next prompt, enter the Password.
 
        Username : admin
        Password : (choose a password for the admin user)
    Repeat the password.
6. At the next prompt, you will be asked "What groups do you want this user to belong to? (Please enter a comma separated list, or leave blank for none)[ ]: ". Leave it blank and press enter.
7. Choose yes for the remaining prompts.

#### Add an Application User

You can pass arguments on the command line or you can run the script interactively.

#### Add an Application User Passing Arguments on the Command Line

You can create the application user non-interactively by passing each argument on the command line. 

The default application user for the quickstarts is `quickstartUser`, in the `ApplicationRealm` realm, with password `quickstartPwd1!`, and belonging to group `guest`. 
To add the default application user, open a command prompt and type the following:

        For Linux:   JBOSS_HOME/bin/add-user.sh -a -u 'quickstartUser' -p 'quickstartPwd1!' -g 'guest'
        For Windows: JBOSS_HOME\bin\add-user.bat  -a -u 'quickstartUser' -p 'quickstartPwd1!' -g 'guest'


##### Add an Application User Interactively 

If you prefer, you can create the application user interactively. 

1. Open a command prompt.
2. Type the command for your operating system

        For Linux:   JBOSS_HOME/bin/add-user.sh
        For Windows: JBOSS_HOME\bin\add-user.bat
3. You should see the following response:

        What type of user do you wish to add? 

        a) Management User (mgmt-users.properties) 
        b) Application User (application-users.properties)
        (a):

    At the prompt, type:  `b`
4. You should see the following response:

        Enter the details of the new user to add.
        Using realm 'ApplicationRealm' as discovered from the existing property files.
        Username :

5. Enter the the Username and at the next prompt, enter the Password. If the quickstart README specifies a Username and Password, enter them here. Otherwise, use the default Username `quickstartUser` and Password `quickstartPwd1!`.
 
        Username : quickstartUser
        Password : quickstartPwd1!
6. At the next prompt, you will be asked "What groups do you want this user to belong to? (Please enter a comma separated list, or leave blank for none)[  ]: ". If the quickstart README specifies the groups to use, enter that here. Otherwise, type the group: `guest`







