from helpers import alphabet_position, rotate_character
from sys import argv, exit


def encrypt(text, rot):
    newString = ""

    for char in text:
        newString += rotate_character(char, rot)

    return newString


def main():
    if(len(argv) == 1):
        print(encrypt(input("Type a message: "), int(input("Rotate by: "))))
    else:
        if(argv[1].isdigit()):
            print(encrypt(input("Type a message: "), int(argv[1])))
        else:
            print("usage: python caesar.py n")
            exit()


if __name__ == "__main__":
    main()
