<?php
require_once(realpath(dirname(__FILE__)) . '/VideoRest.php');
require_once(realpath(dirname(__FILE__)) . '/Chat.php');

use VideoRest;
use Chat;

/**
 * @access public
 * @author mariaroxanaluca
 */
class LiveChat extends Chat {
	/**
	 * @AttributeType VideoRest
	 * /**
	 *  * @AssociationType VideoRest
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_VideoRest_;
}
?>