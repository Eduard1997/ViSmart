<?php
require_once(realpath(dirname(__FILE__)) . '/VideoRest.php');
require_once(realpath(dirname(__FILE__)) . '/Video.php');
require_once(realpath(dirname(__FILE__)) . '/Settings.php');

use VideoRest;
use Video;
use Settings;

/**
 * @access public
 * @author mariaroxanaluca
 */
class VideoStream {
	/**
	 * @AttributeType VideoRest
	 * /**
	 *  * @AssociationType VideoRest
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	public $_VideoRest_;
	/**
	 * @AttributeType Video
	 * /**
	 *  * @AssociationType Video
	 *  * @AssociationMultiplicity *
	 *  * @AssociationKind Aggregation
	 *  * /
	 */

	public $_Settings_;

	/**
	 * @AttributeType LiveChat
	 * /**
	 *  * @AssociationType LiveChat
	 *  * @AssociationMultiplicity 1
	 *  * /
	 */
	private $_LiveChat_;

	
}
?>
