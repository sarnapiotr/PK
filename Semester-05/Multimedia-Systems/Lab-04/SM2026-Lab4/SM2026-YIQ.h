#ifndef SM2026_YIQ_H_
#define SM2026_YIQ_H_

struct YIQ {
  float m_y;
  float m_i;
  float m_q;

  YIQ(float y = 0.0f, float i = 0.0f, float q = 0.0f)
      : m_y(y), m_i(i), m_q(q) {}
};

void setYIQ(int xx, int yy, float y, float i, float q);
YIQ getYIQ(int xx, int yy);

#endif  // SM2026_YIQ_H_