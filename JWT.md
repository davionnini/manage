#JWT 简介

#### 什么是JwT
一个结构紧凑，URL安全，可以在多端传输编码的json格式信息。

### 参考资料
[jwt维护网站](https://jwt.io/ "jwt")         <br>
[RFC文档](https://tools.ietf.org/html/rfc6749#section-1.4 "RFC") <br>
[base64工具网站](https://www.bejson.com/enc/base64/ "base64工具")

###JWT 组成结构
##### header
- typ 类型
- alg 算法
```
{"typ":"jwt","alg":"HS256"}
```
##### payload 存放一些传输的信息(不局限于以下字段)
- iss (issuer)：签发人
- exp (expiration time)：过期时间
- sub (subject)：主题
- aud (audience)：受众
- nbf (Not Before)：生效时间
- iat (Issued At)：签发时间
- jti (JWT ID)：唯一标识
```
{"iss":"joe","exp":1300819380,"sub":"login"}
```

##### signature 由以上两部分base64Url + 密钥 + 签名算法而成。
```
SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```
#### 完整的JWT结构
header 
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

##### 变种
- jws
- jwe
- JWTs

##### 用途
1. 在两端传输数据保护数据完整性
2. 一次性凭证

##### 短板
1. 因为无状态，所以作为token一旦颁发无法销毁
2. 续签
3. 用户状态改变token无法改变
4. 安全问题(CSRF,XSS等)

##### 小提示
- JWT默认是不加密的，但也可以加密，不加密时不宜在jwt中存放敏感信息
- 不要泄露签名密钥
- jwt签发后无法撤回，有效期不宜太长
- JWT泄露会被人冒用身份，为防止盗用，JWT应尽量使用https协议传输

#### 应用场景
用户登录
```mermaid
sequenceDiagram
client ->>server: 我要访问某某资源
server-->>client: 先认证
client ->>server: 完成认证
server-->>client: 下发token
client ->>server: 访问资源
```