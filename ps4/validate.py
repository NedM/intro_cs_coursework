from __future__ import with_statement
import re
import sys

matcher = re.compile("!<(.*?)>!", re.DOTALL)
problems = ['1.A.i', '1.A.ii', '1.A.iii', '1.A.iv', '1.A.v', '1.A.vi',
            '1.A.vii', '1.A.viii', '1.A.ix', '1.B', '2.A', '2.B', '3.A',
            '3.B.i', '3.B.ii', '3.B.iii', '3.B.iv', '3.B.v', '3.B.vi',
            '3.B.vii', '3.C', '3.D', '4']

def main():
    if len(sys.argv) != 2:
        print "usage: validate.py <path_to_answers.txt>"
        return

    with open(sys.argv[1], 'r') as f:
        lines = ''.join(f.readlines())
    matches = matcher.findall(lines)

    assert len(matches) == len(problems), (
            "Mismatch in number of extracted answers; " +
            "num problems: %d num answers: %d" % (
                len(problems), len(matches)))

    for p, a in zip(problems, matches):
        print "Answer for problem (%s):" % p
        print "----------------------------------------"
        print a
        print "----------------------------------------"

if __name__ == "__main__":
    main()
