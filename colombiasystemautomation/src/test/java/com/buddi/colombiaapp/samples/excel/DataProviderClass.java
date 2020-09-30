package com.buddi.colombiaapp.samples.excel;

import org.testng.annotations.DataProvider;



 

public class DataProviderClass {
    /*static XLSXManager prodData = new XLSXManager("target/test-classes/data/TestData.xlsx", "ProductData");
    static XLSXManager userList = new XLSXManager("target/test-classes/data/TestData.xlsx", "Users");
    static XLSXManager articleData = new XLSXManager("target/test-classes/data/TestData.xlsx", "ArticleData");
    static XLSXManager attachmentsData = new XLSXManager("target/test-classes/data/TestData.xlsx", "Attachments");
    static XLSXManager searchData = new XLSXManager("target/test-classes/data/TestData.xlsx","SearchData");
    static XLSXManager themeData = new XLSXManager("target/test-classes/data/TestData.xlsx","ThemeData");*/
    
    public static final String testDataExcelFileName = "testdata.xlsx";	
	private static String fileSeperator = System.getProperty("file.separator");
	private static String testDataExcelFilePath = System.getProperty("user.dir") +fileSeperator+ "testdata";
	private static String testDataExcelFileLocation =  testDataExcelFilePath +fileSeperator+ testDataExcelFileName;
    
   /* public static String getProductName() {
        return prodData.excelReadTextCell(0, 1);
    }

    public static String getModifiedProductName() {
        return prodData.excelReadTextCell(0, 2);
    }

    public static String getReleaseName() {
        return prodData.excelReadTextCell(1, 1);
    }

    public static String getProductIdentifier() {
        return prodData.excelReadTextCell(2, 1);
    }

    public static String getVersionIdentifier() {
        return prodData.excelReadTextCell(3, 1);
    }

    public static String getConfluenceSpacekey() {
        return prodData.excelReadTextCell(4, 1);
    }

    public static String getBaseVersion() {
        return prodData.excelReadTextCell(5, 1);
    }

    public static String getAlias() {
        return prodData.excelReadTextCell(6, 1);
    }

    public static String getModifiedReleaseName() {
        return prodData.excelReadTextCell(1, 2);
    }

    public static String getModifiedProductIdentifier() {
        return prodData.excelReadTextCell(2, 2);
    }


    public static ArticleData getArticleData(int rownum) {
        ArticleData articleDataObject = new ArticleData();
        String[][] test = articleData.getExcelDataFromARow(rownum);
        articleDataObject.setTitle(test[0][0]);
        articleDataObject.setLanguage(test[0][1]);
        articleDataObject.setBody(test[0][2]);
        articleDataObject.setHeadsUpFieldInCAAgileCentralHelpTopic(test[0][3]);
        articleDataObject.setForDevelopersFieldInCAAgileCentralHelpTopic(test[0][4]);
        articleDataObject.setImagePath(test[0][5]);
        articleDataObject.setVideoPath(test[0][6]);
        articleDataObject.setAttachmentPath(test[0][7]);
        articleDataObject.setDownloadCodeFilePathInCAAgileCentralHelpTopic(test[0][8]);
        articleDataObject.setDownloadSamplesFilePathInCAAgileCentralHelpTopic(test[0][9]);
        articleDataObject.setKeywordSearchTermsInCAAgileCentralHelpTopic(test[0][10]);
        articleDataObject.setMenuLinkTitle(test[0][11]);
        articleDataObject.setMenuLinkDescription(test[0][12]);
        articleDataObject.setMenuLinkParentItem(test[0][13]);
        articleDataObject.setMenuLinkWeight(test[0][14]);
        articleDataObject.setUrlAlias(test[0][15]);
        articleDataObject.setArticleType(test[0][16]);
        articleDataObject.setScheduleModerationState(test[0][17]);
        articleDataObject.setDataSheetPath(test[0][18]);
        articleDataObject.setScreenshotPath(test[0][19]);
        return articleDataObject;
    }

    @DataProvider
    public static Object[][] getArticle2Data() {
        return articleData.getExcelDataFromARow(2);
    }

    @DataProvider
    public static Object[][] getArticle3Data() {
        return articleData.getExcelDataFromARow(3);
    }

    @DataProvider
    public static Object[][] getArticle4Data() {
        return articleData.getExcelDataFromARow(4);
    }

    @DataProvider
    public static Object[][] getArticle5Data() {
        return articleData.getExcelDataFromARow(5);
    }

//    @DataProvider
//    public static ArticleData getArticle6Data() {
////        return articleData.getExcelDataFromARow(6);
//        ArticleData articleDataObject = new ArticleData();
//        articleData.setTitle("");
//        return articleData;
//    }

    @DataProvider
    public static Object[][] getArticle7Data() {
        return articleData.getExcelDataFromARow(7);
    }

    @DataProvider
    public static Object[][] getUsers() {
        return userList.getExcelData();
    }

    public static String getTestUserStatus() {
        return userList.excelReadTextCell(5, 4);
    }

    public static String getTestUserNewRole() {
        return userList.excelReadTextCell(5, 5);
    }

    public static User getTestUserDetails() {
        User userObj = new User();
        userObj.setUser(userList.excelReadTextCell(5, 0));
        userObj.setPw(userList.excelReadTextCell(5, 1));
        userObj.setRole(userList.excelReadTextCell(5, 2));
        userObj.setLanguage(userList.excelReadTextCell(5, 3));
        return userObj;
    }
    
    public static User getAllAuthorUserDetails() {
        User userObj = new User();
        userObj.setUser(userList.excelReadTextCell(6, 0));
        userObj.setPw(userList.excelReadTextCell(6, 1));
        userObj.setRole(userList.excelReadTextCell(6, 2));
        userObj.setLanguage(userList.excelReadTextCell(6, 3));
        return userObj;
    }

    public static User getCustomerUserDetails() {
        User userObj = new User();
        userObj.setUser(userList.excelReadTextCell(1, 0));
        userObj.setPw(userList.excelReadTextCell(1, 1));
        userObj.setRole(userList.excelReadTextCell(1, 2));
        userObj.setLanguage(userList.excelReadTextCell(1, 3));
        return userObj;
    }

    public static User getDocReviewerUserDetails() {
        User userObj = new User();
        userObj.setUser(userList.excelReadTextCell(2, 0));
        userObj.setPw(userList.excelReadTextCell(2, 1));
        userObj.setRole(userList.excelReadTextCell(2, 2));
        userObj.setLanguage(userList.excelReadTextCell(2, 3));
        return userObj;
    }

    public static User getDocAdminUserDetails() {
        User userObj = new User();
        userObj.setUser(userList.excelReadTextCell(3, 0));
        userObj.setPw(userList.excelReadTextCell(3, 1));
        userObj.setRole(userList.excelReadTextCell(3, 2));
        userObj.setLanguage(userList.excelReadTextCell(3, 3));
        return userObj;
    }

    public static User getDocAuthorUserDetails() {
        User userObj = new User();
        userObj.setUser(userList.excelReadTextCell(4, 0));
        userObj.setPw(userList.excelReadTextCell(4, 1));
        userObj.setRole(userList.excelReadTextCell(4, 2));
        userObj.setLanguage(userList.excelReadTextCell(4, 3));
        return userObj;
    }
    
    public static int getUsersCount(){
        return userList.getRowCount();
    }
    
    public static int getArticlesCount(){
        return articleData.getRowCount();
    }
    
    public static SearchData getSearchData(int rownum){
        
        SearchData searchDataObject = new SearchData();
        String[][] test = searchData.getExcelDataFromARow(rownum);
                
        searchDataObject.setKeyword(test[0][0]);
        
        return searchDataObject;
        
    }*/
	
	@DataProvider
    public static Object[][] getUsers() {
        return XSSFExcelReader.getExcelData("Alerts");
    }
	
	/*public static User getDocAdminUserDetails() {
        User userObj = new User();
        userObj.setUser(userList.excelReadTextCell(3, 0));
        userObj.setPw(userList.excelReadTextCell(3, 1));
        userObj.setRole(userList.excelReadTextCell(3, 2));
        userObj.setLanguage(userList.excelReadTextCell(3, 3));
        return userObj;
    }*/
	
	
}
