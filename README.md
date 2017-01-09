Need:
    java8+
    maven

说明：
微服务方式分割项目
common，common-facade，parent，runtime4个包是公共包,打成jar包供项目引入

demo-- 一个简易的后台项目
     |
      ----demo-core : demo项目数据层 
     |
      ----demo-domain :demo项目业务vo层
     | 
      ----demo-domainservice：demo项目业务层
     |
      ----demo-extservice：demo项目controller层
     |
      ----demo-facade层：该module需要打成jar包 供前台项目调用
     |
      ----demo-integration:调用第三方项目层
     |
      ----demo-test：单元测试层
     |
      ----demo-web:项目打包层，会把demo项目打成war包
     |
      ----pom.xml :定义项目demo  以及以上若干module       