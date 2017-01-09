<h5>Need: </h5>
    java8+   <br>
    maven    <br>

<h5>说明：</h5>
微服务方式分割项目 <br>
common，common-facade，parent，runtime4个包是公共包,打成jar包供项目引入 <br>

demo-- 一个简易的后台项目                  <br>
     | <br>
      ----demo-core : demo项目数据层       <br>
     | <br>
      ----demo-domain :demo项目业务vo层    <br>
     | <br>
      ----demo-domainservice：demo项目业务层 <br>
     | <br>
      ----demo-extservice：demo项目controller层 <br>
     | <br>
      ----demo-facade层：该module需要打成jar包 供前台项目调用 <br>
     | <br>
      ----demo-integration:调用第三方项目层 <br>
     | <br>
      ----demo-test：单元测试层 <br>
     | <br>
      ----demo-web:项目打包层，会把demo项目打成war包 <br>
     | <br>
      ----pom.xml :定义项目demo  以及以上若干module    <br>    