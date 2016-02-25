import subprocess;
import time;

alias = raw_input('What is your Username: ')
name = raw_input('What is your name: ')
title = raw_input('What is your title: ')
division= raw_input('What is your division: ')
password = raw_input('What is your password: ')

subprocess.Popen("mkdir ../ClientStores/" + alias, shell=True);
subprocess.Popen("keytool -genkeypair -noprompt -alias " + alias + " -keyalg RSA -keysize 2048 -storetype jks -dname \"CN=" + name +", OU=" + title + ", O=" + division + "\" -keystore ../ClientStores/" + alias +"/" + alias + ".jks" + " -storepass " + password +  " -keypass " + password, shell=True);
time.sleep(5)
subprocess.Popen("keytool -certreq -keystore ../ClientStores/" + alias +"/" + alias + ".jks -alias " + alias + " -storepass " + password + " -keyalg rsa -file " + alias + ".csr", shell=True)
time.sleep(2)
subprocess.Popen("openssl x509 -req -CA ../CA/certificate.crt -CAkey ../CA/privateKey.key -in " + alias + ".csr -out " + alias +".pem -days 365 -CAcreateserial -passin pass:password", shell=True)
time.sleep(1)
subprocess.Popen("keytool -noprompt -import -keystore ../ClientStores/" + alias +"/" + alias + ".jks -file ../CA/certificate.crt -alias ca -storepass " + password, shell=True)
time.sleep(1)
subprocess.Popen("keytool -noprompt -storepass " + password + " -import -keystore ../ClientStores/" + alias +"/" + alias + ".jks -file " + alias +".pem -alias " + alias, shell=True)
time.sleep(1)
subprocess.Popen("rm " + alias + ".*", shell=True)
subprocess.Popen("cp ../ClientStores/.clienttruststore ../ClientStores/" + alias + "/clienttruststore", shell=True)
