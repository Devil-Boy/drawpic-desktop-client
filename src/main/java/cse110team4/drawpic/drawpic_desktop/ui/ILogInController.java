/**
 * 
 */
package cse110team4.drawpic.drawpic_desktop.ui;

/**
 * Interface for log in UI
 * @category Interface Segregation
 * @author Kirk
 *
 */
public interface ILogInController {

	public void login();
	
	public void setView(ILogInView view);
}
