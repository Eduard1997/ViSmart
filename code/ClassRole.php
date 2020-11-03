<?php
require_once(realpath(dirname(__FILE__)) . '/User.php');

use User;

/**
 * @access public
 * @author mariaroxanaluca
 */
class ClassRole {
	/**
	 * @AttributeType ClassRole
	 * /**
	 *  * @AssociationType ClassRole
	 *  * /
	 */
	public $_unnamed_ClassRole_;
	/**
	 * @AttributeType User
	 * /**
	 *  * @AssociationType User
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_unnamed_User_;
}
?>