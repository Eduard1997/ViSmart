<?php
require_once(realpath(dirname(__FILE__)) . '/VideoStream.php');
require_once(realpath(dirname(__FILE__)) . '/Account.php');

use VideoStream;
use Account;

/**
 * @access public
 * @author mariaroxanaluca
 */
class ScreenShare {
	/**
	 * @AttributeType VideoStream
	 * /**
	 *  * @AssociationType VideoStream
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_unnamed_VideoStream_;
	/**
	 * @AttributeType Account
	 * /**
	 *  * @AssociationType Account
	 *  * @AssociationKind Composition
	 *  * /
	 */
	public $_unnamed_Account_;
}
?>