<?php
require_once(realpath(dirname(__FILE__)) . '/Account.php');
require_once(realpath(dirname(__FILE__)) . '/VideoStream.php');

use Account;
use VideoStream;

/**
 * @access public
 * @author mariaroxanaluca
 */
class Video {
	private $_volume;
	private $_image;
	/**
	 * @AttributeType Account
	 * /**
	 *  * @AssociationType Account
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_unnamed_Account_;
	/**
	 * @AttributeType VideoStream
	 * /**
	 *  * @AssociationType VideoStream
	 *  * /
	 */
	public $_unnamed_VideoStream_;
}
?>