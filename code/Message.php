<?php
require_once(realpath(dirname(__FILE__)) . '/Account.php');
require_once(realpath(dirname(__FILE__)) . '/Chat.php');
require_once(realpath(dirname(__FILE__)) . '/Attachment.php');

use Account;
use Chat;
use Attachment;

/**
 * @access public
 * @author mariaroxanaluca
 */
class Message {
	/**
	 * @AttributeType Account
	 * /**
	 *  * @AssociationType Account
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_unnamed_Account_;
	/**
	 * @AttributeType Chat
	 * /**
	 *  * @AssociationType Chat
	 *  * /
	 */
	public $_unnamed_Chat_;
	/**
	 * @AttributeType Attachment
	 * /**
	 *  * @AssociationType Attachment
	 *  * @AssociationKind Aggregation
	 *  * /
	 */
	public $_unnamed_Attachment_;
}
?>