<?php
require_once(realpath(dirname(__FILE__)) . '/User.php');

use User;

/**
 * @access public
 * @author mariaroxanaluca
 */
class LoginRest {
	/**
	 * @AttributeType User
	 * /**
	 *  * @AssociationType User
	 *  * /
	 */
	private $_User_;
  private $_db_user_ = "";
  private $_db_password = "";
  private $_db_host = "";

  private $con =

  public function  __construct(){
    self::$con = oci_connect(self::$_db_user_,self::$_db_password,self::$_db_host);
    if (!self::$con) {
        $m = oci_error();
        echo $m['message'], "\n";
        exit;
    }
  }

	  public function getInstance(){
			session_start();
			$_session_ids = $_SESSION["userId"];
			if (asset($_session_id_new) && $_session_id_new=== self::$_session_id)
      {
					return self::$_User_;
      }
		}

		public login($username, $password){
				self::$_session_id = this->getAccessPassword($username,$password);
				if (isset(self::$_session_id))
        {
          create_user_object();
          session_start();
          $_SESSION["userId"] = $user->getUsername();
        }
		}

		public logout($password){
				$_session_id = $_SESSION["userId"];
				if (isset($_session_id))
				{
					session_unset();
					session_destroy();
          unset(self::$_User_);
				}
		}

		public function create_user_object(){
			// now we are enquiring about db for user
			$_User_ = User();

		}

}
?>
