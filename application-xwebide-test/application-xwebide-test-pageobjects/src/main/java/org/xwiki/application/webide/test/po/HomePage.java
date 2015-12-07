/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xwiki.application.webide.test.po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.xwiki.test.ui.po.ViewPage;

/**
 *
 * @author Yann
 */
public class HomePage extends ViewPage {
    
    @FindBy(id = "openCreationBlock")
    private WebElement addNewProjectButton;
    @FindBy(id = "projectName")
    private WebElement projectNameInput;
    @FindBy(css = "div.modal-footer > input.btn.btn-success")
    private WebElement createProjectButton;
    
    public void createProject(String projectName)
    {
        getUtil().gotoPage("WebIDE", "WebHome");
        this.addNewProjectButton.click();
        this.projectNameInput.sendKeys(projectName);
        this.createProjectButton.click();
    }
}
