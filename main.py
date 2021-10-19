x = int(input())
y = int(input())
g = int(input())
# DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
t = y//2 + y % 2
time = 0
score = 0
ast = x
stat = True
fire_stat = True
ast_stat = False
ls = [['*'for j in range(y)]for i in range(x)] + [[' 'for j in range(y)]for i in range(g)] + [[' ' for i in range(y)], ['-' for i in range(72)]]
ls[x+g][t-1] = '@'
if x == 0:
    print("YOU WON!")
    for row in ls:
        for col in row:
            print(col, end='')
        print()
    print("YOUR SCORE:", score)
else:
    for row in ls:
        for col in row:
            print(col, end='')
        print()
    print('Choose your action!')    # The beginning output
while stat:
    if x == 0:
        break
    s = list(input())
    for i in range(len(s)):
        if 65 <= ord(s[i]) <= 90:
            s[i] = chr(ord(s[i])+32)
    time += 1
    if s == ['f', 'i', 'r', 'e']:
        for j in range(1, x+g+1):
            if not fire_stat:
                j = x+g+1
                break
            if ls[x+g-j][t - 1] == '*':
                score += 1
                fire_stat = False
                ls[x+g-j][t-1] = ' '
            elif j == x+g:
                ls[1][t-1] = ' '
                ls[0][t-1] = '|'
                fire_stat = False
                for row in ls:
                    for col in row:
                        print(col, end='')
                    print()
                ls[0][t-1] = ' '
            else:
                ls[x+g-j][t-1] = '|'
                for row in ls:
                    for col in row:
                        print(col, end='')
                    print()
                ls[x + g - j][t - 1] = ' '
        fire_stat = True
    elif s == ['l', 'e', 'f', 't']:
        if 1 < t < y+1:
            t = t-1
            ls[x + g][t] = ' '
            ls[x + g][t - 1] = '@'
    elif s == ['r', 'i', 'g', 'h', 't']:
        if 0 < t < y:
            t = t + 1
            ls[x + g][t - 2] = ' '
            ls[x + g][t - 1] = '@'
    elif s == ['e', 'x', 'i', 't']:
        stat = False
        for row in ls:
            for col in row:
                print(col, end='')
            print()
        print("YOUR SCORE:", score)
        break
    if ls[x+g-1] != [' ' for i in range(y)]:
        ast_stat = True
    if time % 5 == 0:
        if ast_stat and ast == x + g:
            print("GAME OVER")
            for row in ls:
                for col in row:
                    print(col, end='')
                print()
            print("YOUR SCORE:", score)
            break
        elif ast >= x+g:
            for k in range(x + g - 2, g - 2, -1):
                ls[k + 1] = ls[k]
            ls[g - 1] = [' ' for i in range(y)]
        else:
            for k in range(ast - 1, ast - 1 - x, -1):
                ls[k + 1] = ls[k]
            ls[ast - x] = [' ' for i in range(y)]
            ast += 1
    if score == x*y:
        print("YOU WON!")
        for row in ls:
            for col in row:
                print(col, end='')
            print()
        print("YOUR SCORE:", score)
        break
    else:
        for row in ls:
            for col in row:
                print(col, end='')
            print()
        print('Choose your action!')
    ast_stat = False
# DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE