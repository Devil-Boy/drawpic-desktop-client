/**
 * 
 */
package cse110team4.drawpic.drawpic_desktop.ui;

/**
 * @category Interface Segregation
 * @author Kirk
 *
 */
public interface ILogInView {

	public String getInput();
	
	public void setController(final ILogInController controller);
}
