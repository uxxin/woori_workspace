<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Insert Mouse</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        form {
            width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background: #f9f9f9;
        }
        h2 {
            text-align: center;
        }
        label {
            display: block;
            margin: 12px 0 6px;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #aaa;
            border-radius: 5px;
        }
        input[type="submit"] {
            margin-top: 15px;
            width: 100%;
            padding: 10px;
            background: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background: #45a049;
        }
    </style>
</head>
<body>
    <h2>🐭 Add New Mouse</h2>

    <!-- Mouse 등록 Form -->
    <form action="<%=request.getContextPath()%>/mouse/insert" method="post">
        <label for="name">Mouse Name</label>
        <input type="text" id="name" name="name" required>

        <label for="country">Country</label>
        <input type="text" id="country" name="country" required>

        <label for="address">Address</label>
        <input type="text" id="address" name="address" required>

        <input type="submit" value="Register Mouse">
    </form>
</body>
</html>
