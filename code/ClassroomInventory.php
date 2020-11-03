<?php
require_once(realpath(dirname(__FILE__)) . '/User.php');
require_once(realpath(dirname(__FILE__)) . '/Classroom.php');

use User;
use Classroom;

/**
 * @access public
 * @author mariaroxanaluca
 */
class ClassroomInventory {
	/**
	 * @AttributeType User
	 * /**
	 *  * @AssociationType User
	 *  * /
	 */
	public $_User_;
	/**
	 * @AttributeType Classroom
	 * /**
	 *  * @AssociationType Classroom
	 *  * /
	 */
	public $_Classroom_;
}
?>