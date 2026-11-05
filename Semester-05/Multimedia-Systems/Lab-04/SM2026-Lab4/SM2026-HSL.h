#ifndef SM2026_HSL_H_
#define SM2026_HSL_H_

struct HSL {
  float m_h;
  float m_s;
  float m_l;

  HSL(float h = 0.0f, float s = 0.0f, float l = 0.0f)
      : m_h(h), m_s(s), m_l(l) {}
};

void setHSL(int xx, int yy, float h, float s, float l);
HSL getHSL(int xx, int yy);

#endif  // SM2026_HSL_H_