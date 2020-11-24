<?php

require_once 'ClassroomInventory.php';

//todo: get classes for student id;
$classesInventory = new ClassroomInventory();
$classes = $classesInventory->getAllClasses();
echo json_encode($classes);
