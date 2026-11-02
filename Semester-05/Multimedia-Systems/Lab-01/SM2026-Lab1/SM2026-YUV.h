#ifndef SM2026_YUV_H_
#define SM2026_YUV_H_

struct YUV {
  float m_y;
  float m_u;
  float m_v;

  YUV(float y = 0.0f, float u = 0.0f, float v = 0.0f)
      : m_y(y), m_u(u), m_v(v) {}
};

void setYUV(int xx, int yy, float y, float u, float v);
YUV getYUV(int xx, int yy);

#endif  // SM2026_YUV_H_