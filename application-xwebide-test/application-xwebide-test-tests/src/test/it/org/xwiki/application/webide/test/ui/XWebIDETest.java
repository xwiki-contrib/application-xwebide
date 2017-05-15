/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.xwiki.application.webide.test.ui;

import org.junit.Rule;
import org.junit.Test;
import org.xwiki.test.ui.AbstractTest;
import org.xwiki.test.ui.SuperAdminAuthenticationRule;
import org.xwiki.application.webide.test.po.HomePage;
import org.xwiki.application.webide.test.po.ProjectPage;

/**
 *
 * @author Yann
 */
public class XWebIDETest extends AbstractTest {
   
    // Login as superadmin to have delete rights.
    @Rule
    public SuperAdminAuthenticationRule authenticationRule = new SuperAdminAuthenticationRule(getUtil());
    
    @Test
    public void TestXWebIDE() throws Exception {
        String projectSpace = "WebIDE";
        String projectName = "TestProject";
        String projectCode = "TestProjectCode";
        String className = "MyApplication";
        String itemPage = "Item1";
        String contentPage = "ContentPage";
        String existingPage = "TestPage";
        String existingPageSpace = "TestSpace";
        
        // Delete pages that we create in the test
        getUtil().deletePage(projectSpace, projectName);
        getUtil().deletePage(existingPageSpace, existingPage);
        getUtil().deleteSpace(projectName);
        getUtil().deleteSpace(projectCode);
        getUtil().createPage(existingPageSpace, existingPage, "Content", "Test Title");
        
        HomePage homePage = new HomePage();
        ProjectPage project = new ProjectPage();
        
        // Create project
        homePage.createProject(projectName);
        // Create folder
        project.addFolder(projectName);
        // Create content page
        project.addExistingPage(existingPageSpace, existingPage);
        // Create class
        project.addClass(className);
        // Edit page
        project.goToPage(existingPageSpace, existingPage);
        // Add pages in "TestProject"
        project.addPageInTestProjectSpace(contentPage, 0);
        project.goToPage(projectName, contentPage);
        project.addPageInTestProjectSpace(itemPage, 1);
        project.goToObject(projectName, itemPage, projectCode+"."+className+"Class", "pages");
        // Add global objects
        project.addGlobal("js", "JS1");
        project.addGlobal("css", "CSS1");
        project.addGlobal("macros", "MacroTest");
        project.addGlobal("panels", "PanelOne");
        project.addGlobal("uix", "UIX");
        project.addGlobal("translations", "Translations");
        // Delete content page, delete object page
        project.deletePage(projectName, contentPage, "", "pages");
        project.deletePage(projectCode, "CSS", "XWiki.StyleSheetExtension", "css");
        // Edit content
        project.editPage(existingPageSpace, existingPage, "", "pages");
        getUtil().gotoPage(projectSpace, projectName);
        project.editPage(projectCode, "JS", "XWiki.JavaScriptExtension", "js");
        getUtil().gotoPage(projectSpace, projectName);
        
        project.closeLastTab();
        project.closeLastTab();
        project.closeLastTab();
        project.closeLastTab();
        
        project.goToClass(projectCode, className);
    }    
}
