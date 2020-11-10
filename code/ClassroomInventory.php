<?php
require_once(realpath(dirname(__FILE__)) . '/Classroom.php');

use User;
use Classroom;

/**
 * @access public
 * @author mariaroxanaluca
 */
class ClassroomInventory {
	/**
	 * @AttributeType Classroom
	 * /**
	 *  * @AssociationType Classroom
	 *  * /
	 */
	public $_Classroom_;

	__construct($class_array){
		$_Classroom_ = $class_array;
	}
	__construct(){
		$_Classroom_ = array();
	}

	public function getClassRoom(index){
		if (0<= index && index < sizeof($_ClassRoom))
			return $_Classroom_[index];
	}
}
?>
