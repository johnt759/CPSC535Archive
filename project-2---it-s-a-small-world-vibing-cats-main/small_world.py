# CPSC 535 Project 2
# Calculating shortest connection between movie casts

import os # Needed to open text files
import sys # Needed to run command lines

def main(argc, argv):
    # Ensure that the number of arguments must be valid.
    if argc != 2:
        print("Invalid number of arguments.")
        exit(-1)

    #num_casts = 0

    fileline_list = []

    # Read the file and try to open if possible.
    input_file = open(argv[1], "r", encoding="utf8")

    # Obtain all of the contents from the input text file.
    for current_line in input_file:
        fileline_list.append(current_line)
    input_file.close() # Close the file afterwards.

    #print(fileline_list)

    # Begin tidying the lines obtained from the text file.
    
    # If there is a right curly brace at the end of each substring,
    # increment num_casts by 1.
    #for i in range(len(fileline_list)):
        #if fileline_list[i][-1] == "}":
            #num_casts += 1

    # First, create a temporary list.
    #casts = [[] for i in range(num_casts)]
    temp_list = []

    # Now store each cast name in a separate cast list.
    # Be sure to split them into substrings using comma as delimiter.
    cast_list = 0
    for j in range(len(fileline_list)):
        curr_line = fileline_list[j].split(",")
        temp_list.append(curr_line)

    # Finally, tidy up the cast list by removing all unwanted characters.
    # This means remove leading and trailing whitespaces of each cast name
    # and the curly braces.
    for x in range(len(temp_list)):
        for y in range(len(temp_list[x])):
            if "{" in temp_list[x][y]:
                temp_list[x][y] = temp_list[x][y].replace("{", "")
            elif "}" in temp_list[x][y]:
                temp_list[x][y] = temp_list[x][y].replace("}", "")
            temp_list[x][y] = temp_list[x][y].strip()

    # Finally, copy over the contents of temp_list into casts.
    casts = temp_list

    # Print out the complete and tidied cast lists.
    for x in range(len(casts)):
        print(casts[x])

    # If the two casts have at least one string in common (which is actor),
    # then return 1 as the shortest connection of the two casts. If the two
    # casts don't have any string in common, try to look for another cast in
    # a list of n casts. Let tempCast be any cast after the first two casts.
    # If the first cast and tempCast share a common string AND the second cast
    # share a common string as well, then shortest connection will be two.
    # Otherwise, the shortest connection is greater than 2 or 0, which means
    # no connection exists at all.

    # Starting at the first two cast lists, compare each cast name and see
    # if they share a common connection. If so, then simply return the name
    # of the cast.
    for i in range(len(casts[0])):
        for j in range(len(casts[1])):
            if casts[0][i] == casts[1][j]:
                print("Shortest connection=1, Actor={}".format(casts[0][i]))
                return

    is_str_common1, is_str_common2 = False, False
    # Now use temp_cast to search through the cast lists after the first two
    # and check if they have a common connection somewhere. Return the cast
    # list if such a connection exists or return None if otherwise.
    for z in range(2, len(casts)):
        temp_cast = casts[z]
        for w in range(len(temp_cast)):
            temp_name = casts[z][w]
            for x in range(len(casts[0])):
                if temp_name == casts[0][x]:
                    is_str_common1 = True
            for y in range(len(casts[1])):
                if temp_name == casts[1][y]:
                    is_str_common2 = True
        if is_str_common1 == True and is_str_common2 == True:
            print("Shortest connection=2, Cast={}".format(temp_cast))
            return

    # Print this message only if no connection exists.
    print("Shortest connection>2 OR No connection")

if __name__ == "__main__":
    main(len(sys.argv), sys.argv)
