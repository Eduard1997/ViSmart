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
	public $_Account_;
	/**
	 * @AttributeType Chat
	 * /**
	 *  * @AssociationType Chat
	 *  * /
	 */
	public $_Chat_;
	/**
	 * @AttributeType Attachment
	 * /**
	 *  * @AssociationType Attachment
	 *  * @AssociationKind Aggregation
	 *  * /
	 */
	public $_Attachment_;
}
?>