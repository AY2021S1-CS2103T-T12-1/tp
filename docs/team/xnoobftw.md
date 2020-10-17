---
layout: page
title: Xing Yu's Project Portfolio Page
---

## Project: Inventory Book (OneShelf)

OneShelf is an app designed to automate the managing of stock inventory for business owners which translates to savings in man-hours and minimal losses due to over stocking.

Given below are my contributions to the project.

* **Refactoring Address Book 3**: Refactored Person into Item. (Pull request [\#37](https://github.com/AY2021S1-CS2103T-T12-1/tp/pull/37))
  * What it does: Allows CS2103T-T12-1 to start implementing proposed features tailored to our requirements.
  * Justification: This is an essential first step before any features can be implemented as Person class from AB3 is not suitable for our implementation. Variables such as Address and Email has to be removed. Quantity and Supplier have to be added as well.
  * Credits: *AB3 from CS2103T [Repo link](https://github.com/nus-cs2103-AY2021S1/tp)*
  
* **Added Max Quantity Tests**: Added tests for Max Quantity (Pull request [\#88](https://github.com/AY2021S1-CS2103T-T12-1/tp/pull/88))
  
* **Added DeliveryModel**: Added Delivery Model. (Pull request [\#103](https://github.com/AY2021S1-CS2103T-T12-1/tp/pull/103))
  * What it does: Acts as the skeleton for delivery class and commands to be implemented by other memebers.
  * Justification: This is an essential first step before any delivery features can be implemented.

* **Implemented Command History Traversal**: Implemented Command History Traversal (Pull request [\#125](https://github.com/AY2021S1-CS2103T-T12-1/tp/pull/125))
  * What it does: Allows user to traverse their command history with arrow up and down button from GUI.
  * Justification: Synergies our fast typist use case.
  
* **Added CommandHistory Tests**: Added tests for CommandHistory (Pull request [\#125](https://github.com/AY2021S1-CS2103T-T12-1/tp/pull/125))

* **Enhancements to existing features**:
  * Changed AddCommand to add on to existing items instead of throwing an error. An item is considered the same if they have the same name and supplier. (Pull request [\#44](https://github.com/AY2021S1-CS2103T-T12-1/tp/pull/44))
  
* **Documentation**:
  * User Guide:
    * Added documentation for the features `add` and `find`
    * Added documentation for `CommandHistory`

  * Developer Guide:
    * Added Target user profile, value proposition and user stories.
    * Added Command History Traversal under implementation

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=AY2021S1-CS2103T-T12-1&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=zoom&zA=xnoobftw&zR=AY2021S1-CS2103T-T12-1%2Ftp%5Bmaster%5D&zACS=156.8695652173913&zS=2020-08-14&zFS=AY2021S1-CS2103T-T12&zU=2020-09-26&zMG=false&zFTF=commit&zFGS=groupByRepos)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#54](https://github.com/AY2021S1-CS2103T-T12-1/tp/pull/54)
  * Reported bugs (such as: [GUI bug](https://github.com/AY2021S1-CS2103T-T12-1/tp/issues/47))
