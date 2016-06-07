# Required methods when creating a new module

* editor
  * Simple name of the editor which will be used in IDs (e.g : 'wiki', 'object', 'class', etc...)
* load()
  * Load the current page with the current parameters in the displayed tab
  * Must check if there are other tabs to open at the end : if(loadAllTabs) {WebIDE.continueLoadAll();}
* save(tabNumberId, changeSavedContent, continueEditing, saveAll)
  * Save the content in the tab identified by "tabNumberId"
  * @params boolean changeSavedContent : should the value of "savedContent" variable be changed? (true if the tab will still be opened after the save)
  * @params boolean continueEditing : true = stay in the WebIDE, false = redirect to the view mode of the page
  * @params boolean saveAll : true if it has been called by the "save all tabs" button (which means it should call "continueSaveAll(true);" when success)
* getParameters()
  * Get the current value of the parameters from this module. 
  * Parameters are data needed to identified a tab ([locale] for WikiEditor, [objectClass, objectNumber] for ObjectEditor)
  * @out Array parameters : the list of module parameters values
* setParameters(parameters)
  * @params Array parameters : the new value of the module parameters
* resetParameters()
  * Reset the parameters to "null"
* getTabButtonParameters(parameters)
  * Get a string describing parameters with "data-..." attributes
  * @params Array parameters : the parameters to format into string
  * @out String : the list of parameters formatted as HTML attributes ('data-locale="en"' or 'data-object="..." data-objectnumber="0"')
* getParametersFromTab(tabButton)
  * Get the value of the module parameters in a tab
  * @input DOM tabButton : the button element for a tab, with the module parameters as HTML attributes
  * @out Array parameters : the parameters found in attributes of the button
* getMenuElement(folder, file, parameters)
  * Get the DOM element representing the current tab (folder/file/parameters) in the menu (the "link" used to open the tab the first time)
  * @input folder, file, parameters : the data representing a selected tab
  * @out DOM element : the "link" in the menu used to open a tab with folder/file/parameters
* getTabName(folder, file, parameters)
  * Get a name representing the tab (folder/file/parameters)
  * @input folder, file, parameters : the data representing a selected tab
  * @out String : the name to display in the tab button
* getCurrentContent(tabElement)
  * Get a text representing the content of the tab, which is used when the editor checks for unsaved content
  * @input DOM tabElement : the tab main "div"
  * @out Text : the content of the tab
* initCodeMirror(tabId)
  * Initialize CodeMirror for the textarea's in the tab (tabId). The method should initialize CodeMirror for textarea containing code (Cd ObjectEditor.initCodeMirror())
  * @input String tabId : the tab ID
* initHash(hashArray)
  * Initialize the parameters contained in the hash.
  * @input Array hashArray : a list of elements required to identify a tab. Parameters start at the 6th position (hashArray[5] for the first parameter)
* setDeleteButton()
  * Set the action performed when the user clicks on the "Delete" button at the bottom of the page in a tab using this module.
* initMenu()
  * Initialize the links in the menu (Logical/Physical views) related to this module.
  * e.g. : element with the class "editPage" should open a new tab with WikiEditor module.
* initModals()
  * Initialize the modal events related to this module (can be empty of modals aren't used).
  * e.g. : click on the "Add page" button in the modal "Create a new page" should trigger WikiEditor.add(...).

# Additionnal recommended methods

* remove(...)
  * Ability to remove an element from this module (e.g. : an object for ObjectEditor, a page for WikiEditor).
  * Must be managed internally in the module (with initMenu() and initModals() to add the events triggering "remove()")
* add(...)
  * Ability to add an element from this module (e.g. : an object for ObjectEditor, a page for WikiEditor).
  * Must be managed internally in the module (with initMenu() and initModals() to add the events triggering "add()")