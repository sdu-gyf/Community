## 崇新问答

## 资料
[Spring文档](https://spring.io/guides/gs/serving-web-content/)
[外观参考网站](https://elasticsearch.cn/explore)
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
[Bootstrap文档](https://v3.bootcss.com/components)

## 脚本
```sql
create table USER
(
    ID INT auto_increment primary key,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT
);
```
