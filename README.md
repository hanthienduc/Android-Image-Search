Android-Image-Search
====================

Image Search
Search for images from Google
Click on small thumbnail to expand



User Stories:
=============
User can enter a search query that will display a grid of images from Google Images.
  DONE
User can modify advanced search options such as:
  Size (small, medium, large, extra-large)
  Color filter (black, blue, brown, gray, green, etc...)
  Type (faces, photo, clip art, line art)
  Site (espn.com)
  DONE
  
Subsequent searches will have these filters applied to the results
  - need to persist
  DONE
  
User should be able to load more images
  User clicks a “Load More” button in the footer of the GridView
  Optional: User can instead scroll “infinitely” to load more image results
  DONE
  
Apply the filters from the preferences
  DONE
  
Student Learning Actions:
==========================
Explore starting activities with intents that contain user data.
Explore returning a result when an activity is finished.
Explore asynchronously loading images.
Optional: Implementing a common mobile feature of loading new data via scrolling.
 References For This Assignment:

(Guide) Intent Android Docs
(Guide) Accessing and Providing Resources
(Library) Android Async HTTP Client
(Library) Android Universal Image Loader
(StackOverflow) Endless GridView Explanation
(Project) GoogleImageSearcher Project
(Tutorial) Intents Tutorial

Mocks
https://canvas.instructure.com/courses/792753/files/23657413/preview?
https://canvas.instructure.com/courses/792753/files/23657412/preview?

TODO
==================
- Switch to HOLO Theme http://developer.android.com/reference/android/R.style.html#Theme_Holo
- add preferences
- Add icon
- hitting done on the text search box should just do the search
- add pagination
- add swipe left and right
- any way to show some text as well when large image shows