<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>

<h2>${title}</h2>
<p>${message}</p>

<form method="get" action="language">
    <label>${selectLanguage}:</label>
    <select name="lang" onchange="document.cookie = 'lang=' + this.value + ';path=/'; this.form.submit();">
        <option value="en" ${lang == 'en' ? 'selected' : ''}>English</option>
        <option value="vi" ${lang == 'vi' ? 'selected' : ''}>Tiếng Việt</option>
    </select>
</form>

<p>${welcomeMessage}</p>

</body>
</html>