# leagueChallengeComps

### Background
In May 2022, popular online game League of Legends added challenges that can be completed for profile rewards. Some of these challenges require premade teams of 5 to use specific combinations of champions, as shown here:

![leagueChallengeComps1](https://user-images.githubusercontent.com/62409637/168711549-c4682988-93b2-45f3-9868-dc723d91b9e3.JPG)

Since these challenges can be repeated for increasing rewards, and many of them only require 3 out of 5 champions on the team to meet certain requirements, it is efficient to try and complete multiple challenges at once with one team.

### Current Usage

This tool currently allows users to output viable team compositions and their associated challenges to a readable spreadsheet. Viable team compositions are considered teams that contain of 5 unique champions, 1 of which is viable in each role (top, jungle, mid, bottom, and support).

The last 5 columns in champion-data.csv determine whether each champion is viable in each role. These values can be edited to account for individual's champion pools or changes in the meta. All other columns contain each champion's eligibility for individual challenges and should not be changed.

An implementation of this program can be found [here](https://docs.google.com/spreadsheets/d/1jJN_3mmbIPbhcAxE8yQlq94SPVrk6g5JdgUzIYE5Ybs/edit?usp=sharing).

### Possible Additions in the Future
* ability to include/exclude certain champions/challenges
* integration with Riot or OP.GG API to automatically get pick rates
* custom UI
