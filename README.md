# kata-6-complex-method
Refactoring a complex method into simple methods

In this kata, we'll take a method with a ridiculous NPATH complexity that is nearly impossible to unit-test and refactor it into a bunch of simple methods. This refactoring will simplify the main method comlexity drastically, expose the fairly simple domain operation that it performs, and make it easy to cover adequately with unit tests.

To keep the scope of our changes small, we focus on one refactoring step at a time, supported by unit tests (some of which are provided).

Each step is captured in a branch, so you can play with the code and then compare your results with ours, compare the branches to each other, etc. Each branch has an instruction file (@STEP-N.TXT) on what we're trying to accomplish for the next step.

##Instructions for Using this Repo

To use this repo, clone it locally, and use whatever development tools you prefer. You may have to add a dependency to JUnit to build and run the code, depending on your particular setup. The unit test suite is in the test directory, all other files are in the src directory.

This repo includes 7 branches, starting with the original code and progressing through each change step. Start with the original code branch (see below) and follow the @STEP-1.TXT instructions to modify it as suggested. Then compare your results to the next branch, and so on, until the end.
 
###Example:

    1. clone the repo locally:

```
        git clone https://github.com/.../kata-6-complex-method.git
```

    2. CD to the source directory and switch to the first branch, named STEP-1 (yes, uppercase matters)
    
```
        cd kata-6-complex-method
        git checkout STEP-1
```

    3. make sure the code builds and the unit tests pass (JUnit is the only dependency, v4.12)
    4. read the instructions for each step (e.g. @STEP-1.TXT in this case)
    5. modify the code as instructed
    6. run the unit tests (make them pass if they fail; kudos if you refactored carefully enough to not break the tests!)
    7. OPTIONAL BUT FUN: compare your changes to the next branch
    
```
        git diff STEP-1..STEP-2
```

    8. switch to the next branch using the git checkout command as in #2 above (branches are named STEP-22, STEP-23, ..., STEP-7) and repeat from #2 above
       NOTE: if you've made changes, git will whine at you when you try to switch branches; commit or stash your changes to make it shut up
    9. PROFIT! (metaphorically)

##Goals
The technical goal of this exercise is to refactor a complex method into simpler methods.

The learning goals are:

  1. recognize when a single method starts to do "too much", becoming a tree of if-else branches that is difficult to test
  2. know how to safely refactor and combine conditionals, untyped map access, and method objects
  3. help prevent you from writing code like this example in the first place ;) and give you the courage and tools you need to clean such things up when you encounter them

    
##*Suggestions for improvements are always welcome!*

Please [take the survey](https://www.surveymonkey.com/r/VXFH7C8) when done so we can keep making this better!

--Steven

slowe@thoughtworks.com
