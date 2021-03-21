# G2 Medicament Light Client

## Deployement

The deployment has been tested with a Raspberry Pi 4 B + in a private network

1. Install Lamp

```bash
sudo apt install apache2 php libapache2-mod-php mysql-server phpmyadmin
```

2. Restart apache2

```bash
sudo systemctl restart apache2
```

3. Connect to php myadmin through your personnal computer

http://myserveradress/phpmyadmin

4. Create a user to access remotely your db

```mysql

CREATE USER 'monty'@'%' IDENTIFIED BY 'some_pass';
GRANT ALL PRIVILEGES ON *.* TO 'monty'@'localhost' WITH GRANT OPTION;

```

5. Connect to your user through phpmyadmin home menu

6. Create the `projet_medicaments` table

7. Import the tables in your server

If you have any problem with the sql file size : https://stackoverflow.com/questions/3958615/import-file-size-limit-in-phpmyadmin

8. Import the 2 default user

```sql

GRANT SELECT, INSERT, UPDATE ON *.* TO `clientmed`@`%`;

GRANT ALL PRIVILEGES ON `projet_medicaments`.* TO `clientmed`@`%`;

GRANT SELECT, INSERT, UPDATE ON *.* TO `usermanager`@`%`;

GRANT ALL PRIVILEGES ON `projet_medicaments`.* TO `usermanager`@`%`;

```

9. Change manually the password of the users according to the info given in the config.txt files in ressource

10. Install apache tomcat9

```bash
sudo apt install tomcat9
```

11. Use scp to transfer locally your war file

```bash
scp G2MedicamentLightClient.war pi@192.168.0.17:Desktop
```

12. Move from the Desktop to the webapps

```bash
sudo mv /home/pi/G2Me* /var/lib/tomcat9/webapps
```

13. Change your server conf

```xml
<Connector SSLEnabled="true" clientAuth="false" keystoreFile="absolutePathToYour.keystore" keystorePass="groupe2" maxThreads="200" port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol" scheme="https" secure="true" sslProtocol="TLS"/>

```
Info : The key store should be located in /var/lib/tomcat9/webapps/G2MedicamentLightClient/WEB-INF/.keystore

14. Restart your server

```bash
systemctl restart tomcat9
```
