<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%-- Include jQuery library --%>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
     <%-- Include datepicker library --%>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <%-- Initialize datepicker --%>
    <script>
        $(function() {
            $("#datepicker").datepicker();
        });
        

    </script>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
   <style>
        /* Add your CSS styles for a beautiful form */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }

        .container {
            max-width: 400px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            background-color: #4caf50;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
            /* Style the select container */
    .custom-select {
        position: relative;
        display: inline-block;
    }

    /* Style the select element */
    .custom-select select {
        display: inline-block;
        padding: 10px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        width:100%;
    }

    /* Style the arrow icon */
    .custom-select::after {
        content: '\25BC'; /* Unicode character for the down arrow */
        position: absolute;
        top: 50%;
        right: 10px;
        transform: translateY(-50%);
        font-size: 20px;
    }
    </style>
</head>
<body>
     <div class="container" align="center">
        <h2>Employee Registration</h2>
        <form action="/saveEmployee" method="post">
            <label for="employeeName">Employee No:</label>
            <input type="text" id="employeeNo" name="employeeNo" required><br><br>

            <label for="lastName">Employee Name:</label>
            <input type="text" id="empName" name="empName" required><br><br>

            <label for="email">Date of Joining:</label>
            <input type="text" id="datepicker" name="joiningDate" readonly>
            <br><br>
            
            <label for="department">Department:</label>
            <div class="custom-select">
            <select id="department" name="department" required>
            <option value=0>Select Department</option>
             <c:forEach var="dept" items="${departments}">
                    <option value="${dept.id}">${dept.code}</option>
                </c:forEach>
             </select>
             </div>
            <br><br>
            
            
            <label for="salary">Salary:</label>
            <input type="number" id="salary" name="salary" required></input>
            <br><br>
            

            <button type="submit">SAVE</button>
            <button type="reset">CLEAR</button>
        </form>
    </div>   
</body>
</html>