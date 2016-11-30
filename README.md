# Student_Portal

Tests in the app/src/test directory are unit tests and tests in the app/src/anrdoidTest directory are instrumented tests.

Some of the instrumented tests failed to pass because the page cannot be initialized succesffully by using the unit test framework Robololectirc. We add robolectric in the gradle file and import it in the test file but the symbol still cannot be resolved. So we test the UI display by running the application on the emulator. Through clicking the button,new page will be navigated correctly. Possible flows are:

1)register-->login-->main-->create new event/item-->main
2)main-->sliding menu-->profile/event list view/item list view/manage/logout 
3)profile-->edit profile
4)event/item list view-->event/item detail view
5)manage-->events tab-->events I've joined/events I'm interested/events I started
6)manage-->exchange tab-->items I've bought/items I've watchlisted/items I've sold

For the current unit tests, we test anything but UI for the reason explained above. So all onCreate/onClick methods cannot be covered. Also we are still working on writing tests, alghough some classes are not applicable to unit tests because they are all about UI. For example, eventtab is just a fragment implemented by manage activity and the only way to test it is to see the if manage page is loaded correctly. 

To see the code coverage, right click the test folder and choose run tests with coverage. 33% (9/29) classes and are covered. Notice that the report shows that there are 66 classes in com.example.duanli.student_portal package but only 29 of them are source code and all the others are tests. Additionally, 32.8% (86/ 262) methods and 26.7% (278/ 1043) lines are covered.
