<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title><spring:message code="label.submit" /></title>
</head>
<body>
<h2><spring:message code="label.submit" /></h2>

<form:form modelAttribute="userForm" method="post">
  <div>
    <spring:message code="label.username"/>:
    <form:input path="username"/>
    <form:errors path="username" cssClass="error"/>
  </div>
  <div>
    <spring:message code="label.password"/>:
    <form:password path="password"/>
    <form:errors path="password" cssClass="error"/>
  </div>
  <div>
    <spring:message code="label.email"/>:
    <form:input path="email"/>
    <form:errors path="email" cssClass="error"/>
  </div>
  <div>
    <input type="submit" value="<spring:message code='label.submit'/>"/>
  </div>
</form:form>
<a href="?lang=vi">Tiếng Việt</a> | <a href="?lang=en">English</a>

</body>
</html>
