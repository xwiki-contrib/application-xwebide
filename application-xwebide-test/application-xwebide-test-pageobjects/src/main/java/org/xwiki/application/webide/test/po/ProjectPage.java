/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xwiki.application.webide.test.po;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xwiki.test.ui.XWikiWebDriver;
import org.xwiki.test.ui.po.ViewPage;

/**
 
 * @author Yann
 */
public class ProjectPage extends ViewPage {
    
    @FindBy(css = "#hierarchyMainList > li:nth-child(2) > div.hierarchy-toggleCategory")
    private WebElement pagesCategory;
    @FindBy(css = "#hierarchyMainList > li:nth-child(2) > div.btn-group.hierarchyAddNewElement > span")
    private WebElement pagesPlusIcon;
    @FindBy(css = "#hierarchyMainList > li:nth-child(1) > div.hierarchy-toggleCategory")
    private WebElement classesCategory;
    @FindBy(css = "#hierarchyMainList > li:nth-child(1) > div.btn-group.hierarchyAddNewElement > span")
    private WebElement classesPlusIcon;
       
    /**
     * Folders
     */
    @FindBy(css = "#hierarchyMainList > li:nth-child(2) > div.btn-group.hierarchyAddNewElement > ul > li:nth-child(1) > a")
    private WebElement addFolderLink;
    @FindBy(id = "hierarchyNewFolderName")
    private WebElement folderNameInput;
    @FindBy(id = "hierarchyAddFolder")
    private WebElement addFolderButton;
    
    /**
     * Existing Pages
     */
    @FindBy(css = "#hierarchyMainList > li:nth-child(2) > div.btn-group.hierarchyAddNewElement > ul > li:nth-child(2) > a")
    private WebElement addExistingPageLink;
    @FindBy(id = "hierarchyExistingPageName")
    private WebElement existingPageNameInput;
    @FindBy(id = "hierarchyAddExistingPage")
    private WebElement addExistingPageButton;
    
    /**
     * Pages
     */
    @FindBy(id = "hierarchyNewPageName")
    private WebElement pageNameInput;
    @FindBy(id = "hierarchyAddPage")
    private WebElement addPageButton;
    @FindBy(id = "editorDeletePageConfirmed")
    private WebElement deletePageButton;
    @FindBy(id = "editorDeleteObjectConfirmed")
    private WebElement deleteObjectButton;

    /**
     * Classes
     */
    @FindBy(css = "#hierarchyMainList > li:nth-child(1) > div.btn-group.hierarchyAddNewElement > ul > li:nth-child(1) > a")
    private WebElement addClassLink;
    @FindBy(id = "hierarchyClassInput")
    private WebElement classNameInput;
    @FindBy(id = "hierarchyAddClass")
    private WebElement addClassButton;
   
    public void addFolder(String folderName)
    {
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(this.pagesCategory));
        this.displayPlus(this.pagesCategory);
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(this.pagesPlusIcon));
        this.pagesPlusIcon.click();
        this.addFolderLink.click();
        this.folderNameInput.sendKeys(folderName);
        this.addFolderButton.click();
        
        By menuElmt = By.cssSelector(".hierarchy-toggleFolder[data-space=\""+folderName+"\"]");
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.presenceOfElementLocated(menuElmt));
    }
    
    public void addPageInTestProjectSpace(String pageName, Integer isItem)
    {
        WebElement spaceElmt = getDriver().findElementByCssSelector(".hierarchy-toggleFolder[data-space=\"TestProject\"]");
        WebElement plusElmt = getDriver().findElementByXPath("//*[@id=\"hierarchyCategory_pages\"]/li[1]/div[1]/span");
        WebElement linkElmt = getDriver().findElementByXPath("//*[@id=\"hierarchyCategory_pages\"]/li[1]/div[1]/ul/li/a");
        By menuElmt;
        this.openCategory("pages");
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(spaceElmt));
        this.displayPlus(spaceElmt);
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(plusElmt));
        plusElmt.click();
        linkElmt.click();
        this.pageNameInput.sendKeys(pageName);
        if(isItem == 1)
        {
            getDriver().findElementByXPath("//*[@id=\"hierarchyAddPageTypeBlock\"]/label[2]").click();
            menuElmt = By.cssSelector(".editObject[data-page=\""+pageName+"\"][data-space=\"TestProject\"]");
        }
        else
        {
            menuElmt = By.cssSelector(".editPage[data-page=\""+pageName+"\"][data-space=\"TestProject\"]");
        }
        this.addPageButton.click();

        new WebDriverWait(getDriver(),10).until(ExpectedConditions.presenceOfElementLocated(menuElmt));
    }
    
    public void addExistingPage(String spaceName, String pageName)
    {
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(this.pagesCategory));
        this.displayPlus(this.pagesCategory);
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(this.pagesPlusIcon));
        this.pagesPlusIcon.click();
        this.addExistingPageLink.click();
        this.existingPageNameInput.sendKeys(spaceName+"."+pageName);
        this.addExistingPageButton.click();
        
        By menuElmt = By.cssSelector(".editPage[data-page=\""+pageName+"\"][data-space=\""+spaceName+"\"]");
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.presenceOfElementLocated(menuElmt));
    }
    
    public void addClass (String className) throws InterruptedException
    {
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(this.classesCategory));
        this.displayPlus(this.classesCategory);
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(this.classesPlusIcon));
        this.classesPlusIcon.click();
        this.addClassLink.click();
        // TODO: remove next 2 lines when the "Add class" modal will have auto-focus
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(this.classNameInput));
        new Actions(getDriver()).moveToElement(classNameInput).click().perform();
        this.classNameInput.sendKeys(className);
        this.addClassButton.click();
        
        By menuElmt = By.cssSelector(".editAWM[data-page=\""+className+"Class\"]");
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.presenceOfElementLocated(menuElmt));
    }
    
    public void addGlobal(String category, String name)
    {
        WebElement plusIcon = getDriver().findElementByCssSelector("#hierarchyMainList > li:nth-child("+categoryNumber(category)+") > div.btn-group.hierarchyAddNewElement > span");
        WebElement categoryElmt = getDriver().findElementByCssSelector(".hierarchy-toggleCategory[data-category=\""+category+"\"]");
        WebElement addElmtLink = getDriver().findElementByCssSelector("#hierarchyMainList > li:nth-child("+categoryNumber(category)+") > div.btn-group.hierarchyAddNewElement > ul > li > a");
        WebElement addObjectInput = getDriver().findElementById("hierarchyObjectPageInput");
        WebElement addObjectButton = getDriver().findElementById("hierarchyAddObject");
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(categoryElmt));
        this.displayPlus(categoryElmt);
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(plusIcon));
        plusIcon.click();
        addElmtLink.click();
        if(!category.equals("uix"))
        {
            addObjectInput.sendKeys(name);
        }
        addObjectButton.click();

        By menuElmt = By.cssSelector("#hierarchyCategory_"+category+" .wikiinternallink a");
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.presenceOfElementLocated(menuElmt));
    }
    
    public void deletePage(String space, String name, String className, String category) throws InterruptedException
    {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        if(!className.equals(""))
        {
            this.goToObject(space, name, className, category);
        }
        else
        {
            this.goToPage(space, name);
        }
        WebElement wed = getDriver().findElementById("hierarchyDeleteButtonIDE");
        new WebDriverWait(getDriver(),3).until(ExpectedConditions.elementToBeClickable(wed));
        wed.click();
        WebElement menuElmt;
        if(!className.equals(""))
        {
            this.deleteObjectButton.click();
            menuElmt = getDriver().findElement(By.cssSelector(".editObject[data-page=\""+name+"\"][data-space=\""+space+"\"][data-object=\""+className+"\"]"));
        }
        else
        {
            this.deletePageButton.click();
            menuElmt = getDriver().findElement(By.cssSelector(".editPage[data-page=\""+name+"\"][data-space=\""+space+"\"]"));
        }
        new WebDriverWait(getDriver(),3).until(ExpectedConditions.stalenessOf(menuElmt));
    }

    public void editPage(String space, String name, String className, String category) throws InterruptedException
    {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        if(!className.equals(""))
        {
            this.goToObject(space, name, className, category);
            WebElement textarea = getDriver().findElementByCssSelector("#tab_object_"+space+"_"+name+"_"+className.replace(".", "2E")+"_0 .editorCodeField > textarea");
            new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(textarea));
            new Actions(getDriver()).moveToElement(textarea).click().perform();
            textarea.clear();
            textarea.sendKeys("New content of the page, edited by the functional tests.");
        }
        else
        {
            this.goToPage(space, name);
            WebElement textarea = getDriver().findElementById("content_tab_wiki_"+space+"_"+name+"_default");
            new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(textarea));
            new Actions(getDriver()).moveToElement(textarea).click().perform();
            textarea.clear();
            textarea.sendKeys("New content of the page, edited by the functional tests.");
        }
        WebElement wes = getDriver().findElementById("hierarchySaveViewButtonIDE");
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(wes));
        wes.click();
        
        WebElement oldPageElmt = getDriver().findElement(By.id("editorBlock"));
        new WebDriverWait(getDriver(),3).until(ExpectedConditions.stalenessOf(oldPageElmt));
        By pageElmt = By.id("xwikicontent");
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.presenceOfElementLocated(pageElmt));
        String textContent;
        if(!className.equals(""))
        {
            getUtil().gotoPage(space, name, "edit", "editor=object");
            textContent = getDriver().findElementById(className+"_0_code").getText();
        }
        else
        {
            textContent = getDriver().findElementByCssSelector("#xwikicontent > p").getText();
        }
        Assert.assertTrue(textContent.equals("New content of the page, edited by the functional tests."));
    }
    

    public void goToPage(String space, String page) throws InterruptedException
    {
        this.openCategory("pages");
        this.openSpace(space);
        WebElement wep = getDriver().findElement(By.cssSelector(".editPage[data-page=\""+page+"\"][data-space=\""+space+"\"]"));
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(wep));
        wep.click();
    }
    
    public void goToObject(String space, String page, String className, String category) throws InterruptedException
    {
        this.openCategory(category);
        if("pages".equals(category)) this.openSpace(space);
        WebElement weo = getDriver().findElement(By.cssSelector(".editObject[data-page=\""+page+"\"][data-space=\""+space+"\"][data-object=\""+className+"\"]"));
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(weo));
        weo.click();
    }
    
    public void goToClass(String space, String page) throws InterruptedException
    {
        this.openCategory("classes");
        this.openClass(space, page);
        WebElement wec = getDriver().findElement(By.cssSelector(".editAWM[data-page=\""+page+"Class\"][data-space=\""+space+"\"]"));
        new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(wec));
        wec.click();
    }
    
    private void openCategory(String category)
    {
        WebElement wec = getDriver().findElement(By.cssSelector(".hierarchy-toggleCategory[data-category=\""+category+"\"]"));
        WebElement wecContent = getDriver().findElement(By.id("hierarchyCategory_"+category));
        while(!wecContent.isDisplayed())
        {
            new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(wec));
            wec.click();
        }
    }
    
    private void openSpace(String space)
    {
        WebElement wes = getDriver().findElement(By.cssSelector(".hierarchy-toggleFolder[data-space=\""+space+"\"]"));
        WebElement wesContent = getDriver().findElement(By.id("hierarchySpace_"+space));
        while(!wesContent.isDisplayed())
        {
            new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(wes));
            wes.click();
        }
    }
    
    private void openClass(String space, String name)
    {
        WebElement wes = getDriver().findElement(By.id("hierarchyDisplayPage_"+space+"."+name+"Class"));
        WebElement wesContent = getDriver().findElement(By.id("hierarchyPageDetails_Class-"+space+"."+name+"Class"));
        while(!wesContent.isDisplayed())
        {
            new WebDriverWait(getDriver(),10).until(ExpectedConditions.elementToBeClickable(wes));
            wes.click();
        }
    }
    
    public void switchView(String newView)
    {
        
    }
    
    public void closeLastTab()
    {
        WebElement wet = getDriver().findElement(By.cssSelector(".editorTabButton:last-child"));
        wet.click();
        WebElement wetc = wet.findElement(By.className("editorTabButtonCross"));
        wetc.click();
    }
    
    private void displayPlus(WebElement element)
    {
        Actions action = new Actions(getDriver());
        action.moveToElement(element).build().perform();
    }
    
    private Integer categoryNumber(String category)
    {
        if(null != category)
        {
            switch (category) {
                case "classes":
                    return 1;
                case "pages":
                    return 2;
                case "js":
                    return 3;
                case "css":
                    return 4;
                case "macros":
                    return 5;
                case "panels":
                    return 6;
                case "uix":
                    return 8; //TODO
                case "translations":
                    return 7;
                default:
                    return 0;
            }
        }
        return 0;
    }
}