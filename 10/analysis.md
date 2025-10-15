# Use Dynamic Programming solution

- Consider the `*` character is a special character and it couples tightly with the previous character.
- Use two two variable `i` and `j` to mark where the current index is in `input` and `pattern`.

- Base cases:

  - Both `i` and `j` are out of bound `s` and `p` => matching.
  - Only `p` is out of bound => not matching.

- If `i` is out of bound and the base cases are not satisfied => current indices not matching.
- Check matchable of `s[i]` and `p[j]` => current indices matching.
- Check the next character of `p[j]` is `*` or not, If true:
  - Make the decision match zero character => shift `j` twice.
  - If the current `i` and `j` are matching, make the decision match at least one => shift `i` once.
- If the next character is normal and current indices matching:

  - Check the next indices => Shift both `i` and `j` once.

- Otherwise, the current indices is not matching.
