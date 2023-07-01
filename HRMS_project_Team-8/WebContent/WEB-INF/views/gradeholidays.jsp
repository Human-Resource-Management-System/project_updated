<%@page import="models.GradeHoliday,java.util.List,models.HrmsJobGrade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Job Grade Wise Holidays</title>
<style>
/* Add your custom CSS styles here */
body {
	font-family: Arial, sans-serif;
}

h1 {
	color: #333;
	text-align: center;
	margin-bottom: 30px;
}

table {
	margin-top: 20px;
	border-collapse: collapse;
	width: 100%;
	max-width: 800px;
	margin: 0 auto;
	background-color: #fff;
}

th, td {
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
	font-weight: bold;
	color: #333;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

.button {
	display: inline-block;
	padding: 8px 12px;
	font-size: 14px;
	font-weight: bold;
	text-align: center;
	text-decoration: none;
	background-color: #4caf50;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.button.edit {
	background-color: #2196f3;
}

.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
	background-color: #fefefe;
	margin: 10% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
	max-width: 600px;
}

.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
}

.form-row {
	margin-bottom: 16px;
}

.form-row label {
	display: block;
	margin-bottom: 4px;
	font-weight: bold;
	color: #333;
}

.form-row input[type="text"], .form-row input[type="number"] {
	padding: 6px 8px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.form-row input[type="submit"] {
	padding: 8px 16px;
	font-size: 14px;
	font-weight: bold;
	text-align: center;
	text-decoration: none;
	background-color: #4caf50;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.button-container {
	text-align: center;
	margin-top: 20px;
}

.button.add {
	background-color: #ff9800;
	margin-left: auto;
	margin-right: auto;
	display: block;
}
</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function openModal(jobGradeId) {
        $("#editJobGradeId").val(jobGradeId);
        $("#editModal").css("display", "block");
    }

    function closeModal() {
        $("#editModal").css("display", "none");
    }

    function showAddModal() {
        var modal = document.getElementById("addModal");
        var modalContent = document.getElementById("addModalContent");

        // Display the modal
        modal.style.display = "block";

        // Close the modal when the close button is clicked
        var closeButton = document.getElementsByClassName("close")[0];
        closeButton.onclick = function() {
            modal.style.display = "none";
        };
    }

    function addRow() {
        var formData = $("#addForm");

        $.ajax({
            url: "addjobgradeholidays",
            method: "POST",
            data: formData.serialize(),
            success: function(response) {
                console.log(response);
                alert("Added successfully.");
                location.reload();
            },
            error: function(error) {
                console.log(error);
                alert("Something went wrong. Please try again.");
            }
        });
    }

    function editRow() {
        var formData = $("#editForm");

        $.ajax({
            url: "editjobgradeholidays",
            method: "POST",
            data: formData.serialize(),
            success: function(response) {
                console.log(response);
                alert("Updated successfully.");
                location.reload();
            },
            error: function(error) {
                console.log(error);
                alert("Something went wrong. Please try again.");
            }
        });
    }

</script>
</head>
<body>
    <h1>Job Grade Wise Holidays</h1>
    <table>
        <tr>
            <th>Job Grade ID</th>
            <th>Total No of Holidays</th>
            <th></th>
        </tr>

        <% 
            List<GradeHoliday> holidays = (List<GradeHoliday>) request.getAttribute("gradeholidays");
            for (GradeHoliday holiday : holidays) {
        %>
        <tr>
            <td><%= holiday.getJbgr_id() %></td>
            <td><%= holiday.getJbgr_totalnoh() %></td>
            <td>
                <button class="button edit" onclick="openModal('<%= holiday.getJbgr_id() %>')">Edit</button>
            </td>
        </tr>
        <% } %>
    </table>

    <div class="button-container">
        <button class="button add" onclick="showAddModal()">Add</button>
    </div>


    <div id="addModal" class="modal">
        <div id="addModalContent" class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2>Add Job Grade Leaves</h2>
            <form id="addForm">
                <div class="form-row">
                    <select id="addJobGradeId" name="jbgr_id">
                        <option value="">Select Job Grade ID</option>
                        <% List<HrmsJobGrade> jobgrades = (List<HrmsJobGrade>) request.getAttribute("jobgradeinfo");
                            for (HrmsJobGrade jobGrade : jobgrades) { %>
                        <option value="<%= jobGrade.getId() %>"><%= jobGrade.getId() %></option>
                        <% } %>
                    </select>
                </div>

                <div class="form-row">
                    <label for="addHolidayCount">Total No of Holidays:</label>
                    <input type="text" id="addHolidayCount" name="jbgr_totalnoh">
                </div>
                <div class="form-row">
                    <input type="button" class="button" value="Save" onclick="addRow()">
                </div>
            </form>
        </div>
    </div>


    <div id="editModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2>Edit Job Grade Leaves</h2>
            <form id="editForm">
                <div class="form-row">
                    <label for="editJobGradeId">Job Grade ID:</label>
                    <input type="text" id="editJobGradeId" name="jbgr_id" readonly>
                </div>

                <div class="form-row">
                    <label for="editHolidayCount">Total No of Holidays:</label>
                    <input type="text" id="editHolidayCount" name="jbgr_totalnoh">
                </div>
                <div class="form-row">
                    <input type="button" class="button" value="Save" onclick="editRow()">
                </div>
            </form>
        </div>
    </div>
</body>
</html>
