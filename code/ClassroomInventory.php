<?php
/*require_once(realpath(dirname(__FILE__)) . '/Classroom.php');

use User;
use Classroom;*/

require_once 'DBHandler.php';

/**
 * @access public
 * @author mariaroxanaluca
 */
class ClassroomInventory
{
	/**
	 *  * @AssociationType Classroom
	 *  * /
	 */
	public $_Classroom_;

	function __construct()
	{
		$db = new DBHandler();
		$db->makeConnection();
		$this->conn = $db->conn;
	}

	/*public function getClassRoom(index){
		if (0<= index && index < sizeof($_ClassRoom))
			return $_Classroom_[index];
	}*/

	public function getAllClasses()
	{
		$classes = [];
		$sql = "SELECT * from classes";
		$result = $this->conn->query($sql);

		if ($result->num_rows > 0) {
			while ($row = $result->fetch_assoc()) {
				$class = [];
				$class['name'] = $row['name'];
				array_push($classes, $class);
			}
			return $classes;
		}
	}
}
?>
