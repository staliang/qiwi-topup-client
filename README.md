Java client for Qiwi Topup service (https://developer.qiwi.com/ru/topup-wallet/index.html#introduction)

# How can I use it?
First of all, you need add the next repository into your pom.xml
```xml
<repositories>
    <repository>
        <id>qiwi-topup-client-mvn-repo</id>
        <url>https://raw.github.com/staliang/qiwi-topup-client/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>
```
Secondly, add the next dependency.
```xml
<dependencies>
    <dependency>
        <groupId>com.staliang</groupId>
        <artifactId>qiwi-topup-client</artifactId>
        <version>0.3</version>
    </dependency>
</dependencies>
```
Great! Now, you can use it.

