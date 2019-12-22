Java client for Qiwi Topup service (https://developer.qiwi.com/ru/topup-wallet/index.html#introduction)

# What is deleoped in the last version?
- [Get balance](https://developer.qiwi.com/ru/topup-wallet/#get-balance)
- [Check user](https://developer.qiwi.com/ru/topup-wallet/#check-user)
- [Payment](https://developer.qiwi.com/ru/topup-wallet/#payment)
- [Payment's status](https://developer.qiwi.com/ru/topup-wallet/#status)

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

