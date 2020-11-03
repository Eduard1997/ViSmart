<?php
require_once(realpath(dirname(__FILE__)) . '/Classroom.php');
require_once(realpath(dirname(__FILE__)) . '/Chat.php');

use Classroom;
use Chat;

/**
 * @access public
 * @author mariaroxanaluca
 */
class Chanels extends Chat {
	/**
	 * @AttributeType Classroom
	 * /**
	 *  * @AssociationType Classroom
	 *  * /
	 */
	public $_unnamed_Classroom_;
}
?>