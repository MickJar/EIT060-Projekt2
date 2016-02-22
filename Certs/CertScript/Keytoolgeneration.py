import subprocess;
import time;

alias = input('What is your Username: ')
name = input('What is your name: ')
title = input('What is your title: ')
division= input('What is your division: ')
password = input('What is your password: ')

subprocess.Popen("mkdir ../ClientStores/" + alias);
subprocess.Popen("keytool -genkeypair -noprompt -alias " + alias + " -keyalg RSA -keysize 2048 -storetype jks -dname \"CN=" + name +", OU=" + title + ", O=" + division + "\" -keystore ../ClientStores/" + alias +"/" + alias + ".jks" + " -storepass " + password +  " -keypass " + password);
time.sleep(5)
subprocess.Popen("keytool -certreq -keystore ../ClientStores/" + alias +"/" + alias + ".jks -alias " + alias + " -storepass " + password + " -keyalg rsa -file " + alias + ".csr")
time.sleep(2)
subprocess.Popen("openssl x509 -req -CA ../CA/certificate.crt -CAkey ../CA/privateKey.key -in " + alias + ".csr -out " + alias +".pem -days 365 -CAcreateserial -passin pass:password")
time.sleep(1)
subprocess.Popen("keytool -noprompt -import -keystore ../ClientStores/" + alias +"/" + alias + ".jks -file ../CA/certificate.crt -alias ca -storepass " + password)
time.sleep(1)
subprocess.Popen("keytool -noprompt -storepass " + password + " -import -keystore ../ClientStores/" + alias +"/" + alias + ".jks -file " + alias +".pem -alias " + alias)
time.sleep(1)
subprocess.Popen("rm " + alias + ".*")
subprocess.Popen("cp ../ClientStores/.clienttruststore ../ClientStores/" + alias + "/clienttruststore")
